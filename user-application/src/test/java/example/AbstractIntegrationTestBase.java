package example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import example.config.IntegrationTestConfig;
import example.user.UserApplication;
import lombok.extern.log4j.Log4j2;

/**
 * Base class for integration tests, enables running multiple tests
 * with @SpringBootTest
 */
@ExtendWith(SpringExtension.class)
// comment @SpringBootTest and uncomment @EnableFeignClients when application is already running
@SpringBootTest(classes = UserApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@EnableFeignClients(basePackages = { "example.user.service" })
@ContextHierarchy(@ContextConfiguration(classes = IntegrationTestConfig.class))
@ActiveProfiles("local")
@Log4j2
@ImportAutoConfiguration({ FeignAutoConfiguration.class, HttpMessageConvertersAutoConfiguration.class })
@TestPropertySource("classpath:application-local.properties")
public abstract class AbstractIntegrationTestBase {
    @Autowired
    protected Environment environment;

    protected String get(String url, MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        if (mediaType != null) {
            headers.setAccept(Collections.singletonList(mediaType));
        }
        ResponseEntity<String> response = new TestRestTemplate().exchange(url, HttpMethod.GET,
                new HttpEntity<>(headers), String.class);
        log.debug(response.getBody());
        assertTrue(response.getStatusCode() == HttpStatus.OK);
        return response.getBody();
    }
}
