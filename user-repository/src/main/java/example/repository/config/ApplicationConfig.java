package example.repository.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "example")
@EnableWebMvc
@EnableRetry
@Import({ DiscoveryClientConfig.class, UserRepositoryConfig.class })
public class ApplicationConfig {
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        return new RequestLoggingFilter();
    }
}