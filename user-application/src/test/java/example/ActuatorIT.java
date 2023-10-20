package example;



import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class ActuatorIT extends AbstractIntegrationTestBase {
    private static final String STATUS_UP = "\"status\":\"UP\"";
    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    @Autowired
    private String url;

    @Test
    public void test() {
        assertTrue(testRestTemplate.getForObject(url + "/actuator/health", String.class).contains(STATUS_UP));
        assertTrue(testRestTemplate.getForObject(url + "/actuator/health/liveness", String.class).contains(STATUS_UP));
        assertTrue(testRestTemplate.getForObject(url + "/actuator/health/readiness", String.class).contains(STATUS_UP));
    }
}
