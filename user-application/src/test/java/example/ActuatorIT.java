package example;


import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class ActuatorIT extends AbstractIntegrationTestBase {
    private static final String STATUS_UP = "\"status\":\"UP\"";
    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    @Resource
    private String url;

    @Test
    public void test() {
        assertTrue(testRestTemplate.getForObject(url + "/actuator/health", String.class).contains(STATUS_UP));
        assertTrue(testRestTemplate.getForObject(url + "/actuator/health/liveness", String.class).contains(STATUS_UP));
        assertTrue(testRestTemplate.getForObject(url + "/actuator/health/readiness", String.class).contains(STATUS_UP));
    }
}
