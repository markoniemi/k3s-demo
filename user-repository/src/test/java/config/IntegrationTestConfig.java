package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableFeignClients(basePackages = { "example.repository.user" })
public class IntegrationTestConfig {
    @Bean
    public String url() {
        return "http://localhost:8082";
    }
}
