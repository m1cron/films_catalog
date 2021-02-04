package ru.micron.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.micron.model.Role;
import ru.micron.model.Roles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void crudTest() {
        Role role = new Role();
        role.setName(Roles.ROLE_USER.name());
        roleRepository.save(role);
        role.setName(Roles.ROLE_ACTOR.name());
        roleRepository.save(role);
        assertThat(roleRepository.findByName(Roles.ROLE_ACTOR.name())).isNotNull();
        roleRepository.delete(role);
    }

    @Test
    @Sql("role.sql")
    void findByName() {
        assertThat(roleRepository.findByName(Roles.ROLE_USER.name())).isNotEqualTo(Roles.ROLE_USER.name());
        assertThat(roleRepository.findByName(Roles.ROLE_ACTOR.name())).isNotEqualTo(Roles.ROLE_ACTOR.name());
        assertThat(roleRepository.findByName(Roles.ROLE_ADMIN.name())).isNotEqualTo(Roles.ROLE_ADMIN.name());
        assertThat(roleRepository.findByName(Roles.ROLE_PRODUCER.name())).isNotEqualTo(Roles.ROLE_PRODUCER.name());
    }

}