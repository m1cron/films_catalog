package ru.micron.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.micron.persistence.model.Role;
import ru.micron.persistence.model.Roles;
import ru.micron.persistence.repository.RoleRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RoleRepositoryTest {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleRepositoryTest(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Test
    @Sql("role1.sql")
    void crudTest() {
        Role role = new Role();
        role.setName(Roles.USER.name());
        roleRepository.save(role);
        role.setName(Roles.ACTOR.name());
        roleRepository.save(role);
        assertThat(roleRepository.findByName(Roles.ACTOR.name())).isNotNull();
        roleRepository.delete(role);
    }

    @Test
    @Sql("role.sql")
    void findByName() {
        assertThat(roleRepository.findByName(Roles.USER.name())).isNotEqualTo(Roles.USER.name());
        assertThat(roleRepository.findByName(Roles.ACTOR.name())).isNotEqualTo(Roles.ACTOR.name());
        assertThat(roleRepository.findByName(Roles.ADMIN.name())).isNotEqualTo(Roles.ADMIN.name());
        assertThat(roleRepository.findByName(Roles.PRODUCER.name())).isNotEqualTo(Roles.PRODUCER.name());
    }

}