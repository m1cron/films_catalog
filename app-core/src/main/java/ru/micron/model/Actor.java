package ru.micron.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.Hibernate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actor")
public class Actor extends BaseEntity implements Comparable<Actor> {

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "actor_role",
      joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private List<Role> roles = new ArrayList<>();

  @ManyToMany(mappedBy = "actors")
  @Exclude
  private List<Film> films = new ArrayList<>();

  @Override
  public int compareTo(Actor o) {
    return o.getFilms().size() - this.getFilms().size();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Actor actor = (Actor) o;
    return getId() != null && Objects.equals(getId(), actor.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
