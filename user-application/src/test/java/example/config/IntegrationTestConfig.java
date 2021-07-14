package example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.selenium.SeleniumConfig;

@Configuration
@Import({  SeleniumConfig.class })
public class IntegrationTestConfig {
    @Bean
    public String url() {
        return "http://localhost:8083/users";
    }
}
