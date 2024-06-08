package dao.impl;

import dao.Dao;
import model.Author;
import model.Book;
import model.Publisher;
import org.hibernate.*;
import org.hibernate.sql.Update;

import java.sql.Statement;
import java.util.List;

public class PublisherDaoImpl implements Dao<Publisher> {
    private final SessionFactory sessionFactory;

    public PublisherDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Publisher findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Publisher.class, id);
        }
    }

    @Override
    public void save(Publisher publisher) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(publisher);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void update(Publisher publisher) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(publisher);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(Publisher publisher) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(publisher);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<Publisher> findByFirstLetter(String firstLetter) {
        Session session = sessionFactory.openSession();
        List<Publisher> publishers = session.createQuery("FROM Publisher WHERE name LIKE :firstLetter")
                .setParameter("firstLetter", firstLetter + "%")
                .list();
        session.close();
        return publishers;
    }

    @Override
    public List<Publisher> sortByName() {
        return (List<Publisher>) sessionFactory.openSession().createQuery("FROM Publisher p ORDER BY p.name").list();
    }

    public List<Publisher> findByLocation(String location) {
        Session session = sessionFactory.openSession();
        List<Publisher> publishers = session.createQuery("FROM Publisher WHERE location = :location")
                .setParameter("location", location)
                .list();
        session.close();
        return publishers;
    }

    @Override
    public List<Publisher> findAll() {
        return (List<Publisher>) sessionFactory.openSession().createQuery("From Publisher").list();
    }

    public void multipleDelete() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Publisher> publishers = findAll();

            for (Publisher publisher : publishers) {
                session.createQuery("DELETE FROM Publisher b WHERE b.id = :id")
                        .setParameter("id", publisher.getId())
                        .executeUpdate();
            }
            transaction.commit();
        }
    }
}

