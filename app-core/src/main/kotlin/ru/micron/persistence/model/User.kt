package ru.micron.persistence.model

import org.hibernate.Hibernate
import org.springframework.data.annotation.CreatedDate
import org.springframework.util.CollectionUtils
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @Column(name = "uuid", nullable = false)
    var uuid: UUID? = null,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "username", nullable = false)
    var username: String? = null,

    @Column(name = "password", nullable = false)
    var password: String? = null,

    @Column(name = "first_name")
    var firstName: String? = null,

    @Column(name = "last_name")
    var lastName: String? = null,

    @CreatedDate
    @Column(name = "created")
    var created: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ACTIVE")
    var status: Status? = null,

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH]
    )
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "uuid")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: MutableSet<RoleEntity> = mutableSetOf(),

    @ManyToMany(
        fetch = FetchType.EAGER,
        cascade = [CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH]
    )
    @JoinTable(
        name = "user_favourite_film",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "uuid")],
        inverseJoinColumns = [JoinColumn(name = "imdb_id", referencedColumnName = "imdb_id")]
    )
    var favouriteFilms: MutableSet<Film> = mutableSetOf()
) {

    enum class Status {
        ACTIVE, BANNED, DELETED
    }

    fun addRole(role: RoleEntity) {
        roles.add(role)
    }

    fun addToFavourite(film: Film): User {
        favouriteFilms.add(film)
        return this
    }

    fun removeFromFavourite(film: Film): User {
        if (CollectionUtils.isEmpty(favouriteFilms)) {
            return this
        }
        favouriteFilms.remove(film)
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) {
            return false
        }
        val user = other as User
        return uuid != null && uuid == user.uuid
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}