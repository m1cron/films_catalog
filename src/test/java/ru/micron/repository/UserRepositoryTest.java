package ru.micron.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.micron.model.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql("user.sql")
    void findByUsername() {
        User user = new User();
        user.setUsername("DALBOEB");
        assertThat(userRepository.findByUsername("DALBOEB").getUsername()).isNotEqualTo(user.getUsername());
        //assertThat(userRepository.findByUsername("admin").getUsername()).isNotEqualTo("admin");
    }

}