package ru.micron.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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

/*  @Test
  @Sql("role1.sql")
  void crudTest() {
    RoleEntity role = new RoleEntity();
    role.setName(Role.ROLE_USER.name());
    roleRepository.save(role);
    role.setName(Role.ACTOR.name());
    roleRepository.save(role);
    assertThat(roleRepository.findByName(Role.ACTOR.name())).isNotNull();
    roleRepository.delete(role);
  }

  @Test
  @Sql("role.sql")
  void findByName() {
    assertThat(roleRepository.findByName(Role.ROLE_USER.name())).isNotEqualTo(
        Role.ROLE_USER.name());
    assertThat(roleRepository.findByName(Role.ACTOR.name())).isNotEqualTo(Role.ACTOR.name());
    assertThat(roleRepository.findByName(Role.ROLE_ADMIN.name())).isNotEqualTo(
        Role.ROLE_ADMIN.name());
    assertThat(roleRepository.findByName(Role.PRODUCER.name())).isNotEqualTo(Role.PRODUCER.name());
  }*/

}