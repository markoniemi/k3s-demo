package example;

import static example.RestClient.get;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

public class ActuatorIT extends AbstractIntegrationTestBase {
    private static final String STATUS_UP = "\"status\":\"UP\"";
    @Resource
    private String url;

    @Test
    public void health() {
        assertTrue(get(url + "/actuator/health").contains(STATUS_UP));
        assertTrue(get(url + "/actuator/health/liveness").contains(STATUS_UP));
        assertTrue(get(url + "/actuator/health/readiness").contains(STATUS_UP));
    }
}
