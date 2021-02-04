package ru.micron.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

/*    @ManyToMany(mappedBy = "roles")
    private List<User> users;*/

}
