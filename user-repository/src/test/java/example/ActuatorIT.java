package example;

import static example.RestClient.get;
import static example.RestClient.post;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

public class ActuatorIT extends AbstractIntegrationTestBase {
    private static final String STATUS_UP = "\"status\":\"UP\"";
    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    @Resource
    private String url;

    @Test
    public void health() {
        assertTrue(get(url + "/actuator/health").contains(STATUS_UP));
        assertTrue(get(url + "/actuator/health/liveness").contains(STATUS_UP));
        assertTrue(get(url + "/actuator/health/readiness").contains(STATUS_UP));
    }

    @Test
    public void pauseAndStart() {
        assertTrue(get(url + "/actuator/health/readiness").contains(STATUS_UP));
        post(url + "/actuator/system/pause", HttpStatus.OK);
        get(url + "/actuator/health/readiness", HttpStatus.SERVICE_UNAVAILABLE);
        post(url + "/actuator/system/start", HttpStatus.OK);
        assertTrue(get(url + "/actuator/health/readiness").contains(STATUS_UP));
    }
}
