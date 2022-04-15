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
import org.hibernate.Hibernate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "film")
public class Film extends BaseEntity {

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "year")
  private Integer year;

  @Column(name = "genre")
  private String genre;

  @Column(name = "watched")
  private Boolean watched;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "actor_film",
      joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
  private List<Actor> actors = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Film film = (Film) o;
    return getId() != null && Objects.equals(getId(), film.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
