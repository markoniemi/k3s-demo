package example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import example.repository.user.Role;
import example.repository.user.User;
import example.user.service.UserClient;

public class UserRestControllerIT extends AbstractIntegrationTestBase {
    @Autowired
    UserClient userClient;

    @Test
    public void users() {
        User[] users = userClient.findAll();
        assertNotNull(users);
        assertEquals("admin", users[0].getUsername());
    }

    @Test
    public void userByUsername() {
        User user = userClient.findByUsername("admin");
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    public void findById() {
        User user = userClient.findById(1L);
        assertNotNull(user);
        assertEquals("admin", user.getUsername());
    }

    @Test
    public void create() {
        User user = userClient.create(new User("user", "user", "email", Role.ROLE_USER));
        assertNotNull(user);
        assertEquals("user", user.getUsername());
        user = userClient.findById(user.getId());
        assertNotNull(user);
        userClient.delete(user.getId());
        user = userClient.findById(user.getId());
        assertNull(user);
    }

    @Test
    public void count() {
        assertEquals(1L, userClient.count());
    }
}
