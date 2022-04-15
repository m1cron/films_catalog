package ru.micron.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.micron.persistence.model.Role;
import ru.micron.persistence.model.Roles;
import ru.micron.persistence.model.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ru.micron.persistence.repository.UserRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    @Sql("user.sql")
    void crudTest() {
        assertThat(userRepository.findUserByUsername("user").getUsername()).isEqualTo("user");
        assertThat(userRepository.findUserByUsername("user").getPassword()).isEqualTo("123");
        assertThat(userRepository.findUserByUsername("admin").getUsername()).isEqualTo("admin");
        assertThat(userRepository.findUserByUsername("admin").getPassword()).isEqualTo("123");

        User test = new User();
        test.setUsername("test");
        test.setPassword("test_pass");
        test.setEmail("mail@email.com");
        userRepository.save(test);
        assertThat(userRepository.findUserByUsername("test").getUsername()).isEqualTo("test");
        assertThat(userRepository.findUserByUsername("test").getPassword()).isEqualTo("test_pass");
        assertThat(userRepository.findUserByUsername("test").getEmail()).isEqualTo("mail@email.com");
    }

    @Test
    void rolesTest() {
        User test1 = new User();
        test1.setUsername("test1");
        test1.setPassword("test1_pass");
        test1.setEmail("mail1@email.com");

        Role role0 = new Role();
        role0.setName(Roles.ADMIN.name());
        Role role1 = new Role();
        role1.setName(Roles.PRODUCER.name());

        List<Role> roles = Stream.of(role0, role1).collect(Collectors.toList());
        test1.setRoles(roles);
        userRepository.save(test1);
        List<Role> responce = userRepository.findUserByUsername("test1").getRoles();
        assertThat(responce.get(0).getName()).isEqualTo(Roles.ADMIN.name());
        assertThat(responce.get(1).getName()).isEqualTo(Roles.PRODUCER.name());
    }

}