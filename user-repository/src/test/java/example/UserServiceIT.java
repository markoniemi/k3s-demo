package example;

import static example.RestClient.get;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import example.repository.user.User;
import example.service.user.UserClient;
import io.restassured.common.mapper.TypeRef;

public class UserServiceIT extends AbstractIntegrationTestBase {
    @Autowired
    private String url;
    @Autowired
    private UserClient userClient;
    @Test
    public void getUsersRest() throws JsonParseException, JsonMappingException, IOException {
        List<User> users = get(url + "/api/rest/users/v1", userListType());
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    public void getUserRest() throws JsonParseException, JsonMappingException, IOException {
        User user = get(url + "/api/rest/users/v1?username=admin", User.class);
        assertEquals("admin", user.getUsername());
    }
    
    @Test
    public void getUsersFeign() throws JsonParseException, JsonMappingException, IOException {
        User[] users = userClient.findAll();
        assertNotNull(users);
        assertEquals(1, users.length);
    }
	private TypeRef<List<User>> userListType() {
		return new TypeRef<List<User>>() {
		};
	}
}
