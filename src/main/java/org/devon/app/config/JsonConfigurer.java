package org.devon.app.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JsonConfigurer {

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
//        https://mkyong.com/java/jackson-was-expecting-double-quote-to-start-field-name/
        ObjectMapper objectMapper = builder.build();
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        return objectMapper;
    }

}
