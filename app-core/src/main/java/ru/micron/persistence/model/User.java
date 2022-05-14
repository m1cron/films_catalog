package ru.micron.persistence.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.util.CollectionUtils;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  private UUID uuid;

  @Column(name = "email")
  private String email;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @CreatedDate
  @Column(name = "created")
  private LocalDateTime created;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, columnDefinition = "ACTIVE")
  private Status status;

  public enum Status {
    ACTIVE,
    BANNED,
    DELETED
  }

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER,
      cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "uuid"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private List<RoleEntity> roles;

  public void addRole(RoleEntity role) {
    if (CollectionUtils.isEmpty(roles)) {
      roles = new ArrayList<>();
    }
    roles.add(role);
  }

  @ToString.Exclude
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_favourite_film",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "uuid"),
      inverseJoinColumns = @JoinColumn(name = "imdb_id", referencedColumnName = "imdb_id"))
  private Set<Film> favouriteFilms;

  public User addToFavourite(Film film) {
    if (favouriteFilms == null) {
      favouriteFilms = new HashSet<>();
    }
    favouriteFilms.add(film);
    return this;
  }

  public User removeFromFavourite(Film film) {
    if (CollectionUtils.isEmpty(favouriteFilms)) {
      return this;
    }
    favouriteFilms.remove(film);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    User user = (User) o;
    return uuid != null && Objects.equals(uuid, user.uuid);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
