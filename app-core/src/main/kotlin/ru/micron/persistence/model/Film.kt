package ru.micron.persistence.model

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "film")
data class Film(
    @Id @Column(name = "imdb_id", nullable = false) var imdbID: String? = null,

    @Column(name = "title") var title: String? = null,

    @Column(name = "year_of_release") var year: String? = null,

    @Column(name = "rated") var ageRate: String? = null,

    @Column(name = "released") var releaseYear: String? = null,

    @Column(name = "runtime") var filmTime: String? = null,

    @Column(name = "genre") var genres: String? = null,

    @Column(name = "director") var director: String? = null,

    @Column(name = "writer") var writers: String? = null,

    @Column(name = "actor") var actors: String? = null,

    @Column(name = "plot") var plot: String? = null,

    @Column(name = "language_of") var language: String? = null,

    @Column(name = "country") var country: String? = null,

    @Column(name = "award") var awards: String? = null,

    @Column(name = "poster") var posterUrl: String? = null,

    @Column(name = "metascore") var metascore: String? = null,

    @Column(name = "imdb_rating") var imdbRating: String? = null,

    @Column(name = "imdb_vote") var imdbVotes: String? = null,

    @Column(name = "type") var type: String? = null,

    @Column(name = "dvd") var dvd: String? = null,

    @Column(name = "box_office") var boxOffice: String? = null,

    @Column(name = "production") var production: String? = null,

    @Column(name = "website") var website: String? = null,

    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "film",
        cascade = [CascadeType.ALL]
    ) var ratings: List<FilmRating> = mutableListOf(),

    @ManyToMany(
        fetch = FetchType.EAGER,
        mappedBy = "favouriteFilms",
        cascade = [CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH]
    ) var users: MutableSet<User> = mutableSetOf()
) {

    fun addUserToFilm(user: User) {
        users.add(user)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) {
            return false
        }
        val film = other as Film
        return imdbID != null && imdbID == film.imdbID
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}