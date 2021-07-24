package config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = { "example.service.user" })
public class IntegrationTestConfig {
    @Bean
    public String url() {
        return "http://localhost:8082";
    }
}
