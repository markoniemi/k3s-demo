package example.repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import example.repository.user.Role;
import example.repository.user.User;
import example.repository.user.UserRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DatabaseInitializer {
	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void initialize() {
		try {
			if (userRepository.findByUsername("admin") == null) {
				log.debug("DatabaseInitializer.initialize");
				userRepository.save(new User("admin", "admin", "admin@test.com", Role.ROLE_ADMIN));
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
