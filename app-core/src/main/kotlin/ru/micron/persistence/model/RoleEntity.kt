package ru.micron.persistence.model

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "roles")
data class RoleEntity(
    @Id
    var id: UUID? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    var name: ERole? = null,

    @JsonBackReference
    @ManyToMany(
        fetch = FetchType.EAGER,
        mappedBy = "roles",
        cascade = [CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH]
    )
    var users: MutableList<User> = mutableListOf()
) {

    enum class ERole {
        ROLE_ADMIN, ROLE_USER
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as RoleEntity
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}