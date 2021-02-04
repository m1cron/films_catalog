package ru.micron.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "films")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private Integer year;

    @Column(name = "genre")
    private String genre;

    @Column(name = "watched")
    private Boolean watched;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id")
    private List<User> users;

}