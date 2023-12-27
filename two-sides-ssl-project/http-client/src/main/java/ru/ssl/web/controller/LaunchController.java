package ru.ssl.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.ssl.web.dto.ClientResponse;

import static lombok.AccessLevel.PRIVATE;
import static ru.ssl.utils.Constants.ENDPOINT;
import static ru.ssl.utils.Constants.SERVER_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/launch")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class LaunchController {

    RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<ClientResponse> getData() {
        String url = getUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "ssl-test");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ClientResponse clientResponse;
        ResponseEntity<String> response;

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (Exception e) {

            clientResponse = new ClientResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity.ok(clientResponse);
        }

        clientResponse = new ClientResponse(response.getBody(), response.getStatusCode());
        return ResponseEntity.ok(clientResponse);
    }

    private String getUrl() {
        return String.format("%s%s", SERVER_URL, ENDPOINT);
    }
}
