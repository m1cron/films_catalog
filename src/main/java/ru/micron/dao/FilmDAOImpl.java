package ru.micron.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.micron.model.Film;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class FilmDAOImpl implements FilmDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Film> allFilms() {
        Query query = entityManager.createQuery("from Film");
        return (List<Film>) query.getResultList();
    }

    @Override
    public void add(Film film) {
        entityManager.merge(film);
    }

    @Override
    public void delete(Film film) {
        Query query = entityManager.createQuery("delete from Film where id=:film_id");
        query.setParameter("film_id", film.getId());
        query.executeUpdate();
    }

    @Override
    public void edit(Film film) {
        entityManager.merge(film);
    }

    @Override
    public Film getById(int id) {
        return entityManager.find(Film.class, id);
    }
}