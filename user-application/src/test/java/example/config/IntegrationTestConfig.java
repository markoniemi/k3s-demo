package example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.selenium.SeleniumConfig;

@Configuration
@Import({  SeleniumConfig.class })
public class IntegrationTestConfig {
}
