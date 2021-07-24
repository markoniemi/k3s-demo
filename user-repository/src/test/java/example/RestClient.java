package example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClient {
    public static String get(String url) {
        return get(url, HttpStatus.OK);
    }

    public static String get(String url, HttpStatus httpStatus) {
        return execute(HttpMethod.GET, url, httpStatus, String.class).getBody();
    }

    public static <T> T get(String url, Class<T> responseType) {
        return execute(HttpMethod.GET, url, HttpStatus.OK, responseType).getBody();
    }

    public static String post(String url) {
        return post(url, HttpStatus.OK);
    }

    public static String post(String url, HttpStatus httpStatus) {
        return execute(HttpMethod.POST, url, httpStatus, String.class).getBody();
    }

    private static <T> ResponseEntity<T> execute(HttpMethod httpMethod, String url, HttpStatus httpStatus,
            Class<T> responseType) {
        ResponseEntity<T> response = new TestRestTemplate().exchange(url, httpMethod, null, responseType);
        assertTrue(response.getStatusCode() == httpStatus, response.getStatusCode().toString());
        return response;
    }

    public static <T> List<T> parseList(String json, Class<T> listType)
            throws JsonMappingException, JsonProcessingException {
        return new ObjectMapper().readValue(json, new TypeReference<List<T>>() {
        });
    }
}
