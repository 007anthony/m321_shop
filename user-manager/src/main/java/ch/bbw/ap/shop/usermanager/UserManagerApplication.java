package ch.bbw.ap.shop.usermanager;

import ch.bbw.ap.shop.m321shopcore.security.Role;
import ch.bbw.ap.shop.usermanager.model.User;
import ch.bbw.ap.shop.usermanager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "ch.bbw.ap.shop")
public class UserManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagerApplication.class, args);
    }

    @Bean
    CommandLineRunner initDb(UserRepository userRepository) {

        return (args) -> {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@example.com");
            adminUser.setHashedPassword("$2a$10$/KNq.2AoxG7O9cLmMrhLYu2HYGeXGdshe3JdYHhnKpXUBuTnjZMYy");
            adminUser.setRole(Role.ADMIN);

            userRepository.save(adminUser);

            User testUser = new User();
            testUser.setUsername("test");
            testUser.setEmail("test@example.com");
            testUser.setHashedPassword("$2a$10$OEhATMzD06gwSLdCEcS5xOvbJz98/Zz75FNJ4z4xdiX/onL0ZeR1.");
            testUser.setRole(Role.USER);

            userRepository.save(testUser);
        };
    }

}
