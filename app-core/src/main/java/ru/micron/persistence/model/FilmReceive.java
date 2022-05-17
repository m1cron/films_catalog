package ru.micron.persistence.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "film_receive")
public class FilmReceive {

  @Id
  @Column(name = "imdb_id")
  private String imdbId;

  @Column(name = "is_received")
  private Boolean isReceived;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    FilmReceive that = (FilmReceive) o;
    return imdbId != null && Objects.equals(imdbId, that.imdbId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
