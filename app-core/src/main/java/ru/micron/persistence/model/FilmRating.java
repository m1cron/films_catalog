package ru.micron.persistence.model;

import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "film_rating")
public class FilmRating {

  @Id
  @Column(name = "uuid")
  private UUID uuid;

  @Column(name = "imdb_id")
  private String imdbId;

  @Column(name = "source")
  private String source;

  @Column(name = "value")
  private String value;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "imdb_id", insertable = false, updatable = false)
  private Film film;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    FilmRating that = (FilmRating) o;
    return uuid != null && Objects.equals(uuid, that.uuid);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}