package ru.micron.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.micron.model.Film;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class FilmDAOImpl implements FilmDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Film> allFilms() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Film").getResultList();
    }

    @Override
    public void add(Film film) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(film);
    }

    @Override
    public void delete(Film film) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(film);
    }

    @Override
    public void edit(Film film) {
        Session session = entityManager.unwrap(Session.class);
        session.update(film);
    }

    @Override
    public Film getById(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Film.class, id);
    }
}