package ru.micron.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;


@Getter
@Setter
@ToString
@Accessors(chain = true)
@RequiredArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {

  @Id
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @JsonBackReference
  @ToString.Exclude
  @ManyToMany(mappedBy = "roles", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
  private List<User> users;

  public RoleEntity(String role) {
    id = UUID.nameUUIDFromBytes(role.getBytes(StandardCharsets.UTF_8));
    this.name = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    RoleEntity that = (RoleEntity) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
