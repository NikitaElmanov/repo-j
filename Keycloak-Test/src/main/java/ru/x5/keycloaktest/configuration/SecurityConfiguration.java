package ru.x5.keycloaktest.configuration;

import com.nimbusds.oauth2.sdk.util.MapUtils;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.ClaimAccessor;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SecurityConfiguration {

    @NonFinal
    @Value("${spring.security.oauth2.client.provider.keycloak.user-name-attribute}")
    String principleAttribute;

    @NonFinal
    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    String clientId;

    String DEFAULT_ROLE_PREFIX = "ROLE_";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        http.oauth2Login(Customizer.withDefaults());

        http.authorizeHttpRequests(authorizeHttpRequests ->
                authorizeHttpRequests
                        .anyRequest()
                        .authenticated()
        );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var converter = new JwtAuthenticationConverter();
        var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        converter.setPrincipalClaimName(principleAttribute);
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
                    var authorities = jwtGrantedAuthoritiesConverter.convert(jwt);
                    var roles = extractResourceRoles(jwt);

                    return Stream.concat(
                                    authorities.stream(),
                                    roles.stream())
                            .toList();
                }
        );

        return converter;
    }

    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oAuth2UserService() {
        var oidcUserService = new OidcUserService();
        return userRequest -> {
            var oidcUser = oidcUserService.loadUser(userRequest);
            var roles = extractResourceRoles(oidcUser);

            var authorities = Stream.concat(
                            oidcUser.getAuthorities().stream(),
                            roles.stream())
                    .toList();

            return new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
        };
    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(ClaimAccessor claimAccessor) {
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;

        resourceAccess = claimAccessor.getClaimAsMap("resource_access");
        if (MapUtils.isEmpty(resourceAccess)) {
            return Collections.emptyList();
        }

        resource = (Map<String, Object>) resourceAccess.get(clientId);

        if (MapUtils.isEmpty(resource)) {
            return Collections.emptyList();
        }

        resourceRoles = (Collection<String>) resource.get("roles");

        if (CollectionUtils.isEmpty(resourceRoles)) {
            return Collections.emptyList();
        }

        return resourceRoles.stream()
                .map(role -> Strings.concat(DEFAULT_ROLE_PREFIX, role))
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority.class::cast)
                .toList();
    }
}
