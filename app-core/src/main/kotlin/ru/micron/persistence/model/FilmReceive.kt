package ru.micron.persistence.model

import org.hibernate.Hibernate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "film_receive")
data class FilmReceive(
    @Id
    @Column(name = "imdb_id", nullable = false)
    var imdbId: String? = null,

    @Column(name = "is_received", nullable = false)
    var isReceived: Boolean? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) {
            return false
        }
        val that = other as FilmReceive
        return imdbId != null && imdbId == that.imdbId
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}