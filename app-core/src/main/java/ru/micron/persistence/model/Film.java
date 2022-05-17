package ru.micron.persistence.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "film")
public class Film {

  @Id
  @Column(name = "imdb_id")
  private String imdbID;

  @Column(name = "title")
  private String title;

  @Column(name = "year_of_release")
  private String year;

  @Column(name = "rated")
  private String ageRate;

  @Column(name = "released")
  private String releaseYear;

  @Column(name = "runtime")
  private String filmTime;

  @Column(name = "genre")
  private String genres;

  @Column(name = "director")
  private String director;

  @Column(name = "writer")
  private String writers;

  @Column(name = "actor")
  private String actors;

  @Column(name = "plot")
  private String plot;

  @Column(name = "language_of")
  private String language;

  @Column(name = "country")
  private String country;

  @Column(name = "award")
  private String awards;

  @Column(name = "poster")
  private String posterUrl;

  @Column(name = "metascore")
  private String metascore;

  @Column(name = "imdb_rating")
  private String imdbRating;

  @Column(name = "imdb_vote")
  private String imdbVotes;

  @Column(name = "type")
  private String type;

  @Column(name = "dvd")
  private String dvd;

  @Column(name = "box_office")
  private String boxOffice;

  @Column(name = "production")
  private String production;

  @Column(name = "website")
  private String website;

  @ToString.Exclude
  @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
  private List<FilmRating> ratings = new ArrayList<>();

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "favouriteFilms", cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
  private Set<User> users;

  public void addUserToFilm(User user) {
    if (users == null) {
      users = new HashSet<>();
    }
    users.add(user);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Film film = (Film) o;
    return imdbID != null && Objects.equals(imdbID, film.imdbID);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
