package com.example.gestionquestion;

import com.example.gestionquestion.entities.Role;
import com.example.gestionquestion.entities.RoleName;
import com.example.gestionquestion.entities.User;
import com.example.gestionquestion.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@AllArgsConstructor

public class GestionQuestionApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GestionQuestionApplication.class, args);
    }
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        this.userRepository.deleteAll();
        Set<Role> roles1 = new HashSet<>();
        Set<Role> roles2 = new HashSet<>();

        Role roleTest = Role.builder()
                .role(RoleName.ROLE_ADMIN)
                .build();
        roles1.add(roleTest);

        User user1 = User.builder()
                .firstName("Soufiane")
                .lastName("Allamou")
                .username("Soufiane99")
                .Password(passwordEncoder.encode("Dimahmd123"))
                .roles(roles1)
                .build();
        roleTest.setUser(user1);
        this.userRepository.save(user1);

    }
}
