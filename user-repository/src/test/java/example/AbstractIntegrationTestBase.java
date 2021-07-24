package example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.IntegrationTestConfig;
import example.repository.UserRepositoryApplication;

/**
 * Base class for integration tests, enables running multiple tests
 * with @SpringBootTest
 */
@ExtendWith(SpringExtension.class)
// comment @SpringBootTest and uncomment @EnableFeignClients when application is already running
@SpringBootTest(classes = UserRepositoryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextHierarchy(@ContextConfiguration(classes = IntegrationTestConfig.class))
@ActiveProfiles("h2")
@ImportAutoConfiguration({ FeignAutoConfiguration.class, HttpMessageConvertersAutoConfiguration.class })
public class AbstractIntegrationTestBase {
    @Autowired
    protected Environment environment;

    @Test
    public void dummy() {
        assertTrue(true);
    }
}
