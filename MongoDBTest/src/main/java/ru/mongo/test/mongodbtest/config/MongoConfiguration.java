package ru.mongo.test.mongodbtest.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import java.net.UnknownHostException;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class MongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.userName}")
    private String userName;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.port}")
    private int port;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.authentication-database}")
    private String authenticationDb;

//    @Bean
//    @Primary
//    public MongoCustomConversions customConversions() {
//        return new MongoCustomConversions(
//                Arrays.asList(
//                        new OkvedReadConverter(),
//                        new RegisterTemplateBreadcrumbsConverter(),
//                        new BigDecimalWriterConverter(),
//                        new BigDecimalReaderConverter()
//                ));
//    }

    @Bean
    @Primary
    public MongoTemplate template(MongoClient client) throws UnknownHostException {
        MongoTemplate mongoTemplate = new MongoTemplate(client, database);

        MappingMongoConverter conv = (MappingMongoConverter) mongoTemplate.getConverter();
//        conv.setCustomConversions(customConversions());
        conv.afterPropertiesSet();
        return mongoTemplate;
    }

    @Bean
    public MongoClient client() {
        CodecRegistry registry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(
                        PojoCodecProvider.builder()
                                .register(Class.class)
                                .build()
                )
        );

        return MongoClients.create(MongoClientSettings.builder()
                                           .applyConnectionString(getConnectionString())
                                           .codecRegistry(registry)
                                           .uuidRepresentation(UuidRepresentation.STANDARD)
                                           .build());
    }

    private ConnectionString getConnectionString() {
        StringBuilder sb = new StringBuilder();
        sb.append("mongodb://");

        if (userName != null && password != null) {
            sb.append(userName)
                    .append(":")
                    .append(password)
                    .append("@");
        }

        sb.append(host)
                .append(":")
                .append(port)
                .append("/")
                .append("?authSource=")
                .append(authenticationDb);

        return new ConnectionString(sb.toString());
    }
}

