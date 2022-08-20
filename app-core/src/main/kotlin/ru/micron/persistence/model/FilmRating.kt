package ru.micron.persistence.model

import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "film_rating")
data class FilmRating(
    @Id
    @Column(name = "uuid", nullable = false)
    var uuid: UUID? = null,

    @Column(name = "imdb_id")
    var imdbId: String? = null,

    @Column(name = "source")
    var source: String? = null,

    @Column(name = "value")
    var value: String? = null,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "imdb_id", insertable = false, updatable = false)
    val film: Film? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) {
            return false
        }
        val that = other as FilmRating
        return uuid != null && uuid == that.uuid
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}