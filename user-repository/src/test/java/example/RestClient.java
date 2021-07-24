package example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestClient {
    public static String get(String url) {
        return get(url, HttpStatus.OK);
    }

    public static String get(String url, HttpStatus httpStatus) {
        return execute(HttpMethod.GET, url, httpStatus);
    }

    public static String post(String url) {
        return post(url, HttpStatus.OK);
    }

    public static String post(String url, HttpStatus httpStatus) {
        return execute(HttpMethod.POST, url, httpStatus);
    }

    private static String execute(HttpMethod httpMethod, String url, HttpStatus httpStatus) {
        ResponseEntity<String> response = new TestRestTemplate().exchange(url, httpMethod, null, String.class);
        assertTrue(response.getStatusCode() == httpStatus);
        return response.getBody();
    }
}
