package dao.impl;

import dao.Dao;
import model.Author;
import model.Book;
import model.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BookDaoImpl implements Dao<Book> {
    private final SessionFactory sessionFactory;

    public BookDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Book findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Book.class, id);
        }
    }

    @Override
    public void save(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void update(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public void delete(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(book);
            transaction.commit();
            session.close();
        }
    }

    @Override
    public List<Book> findByFirstLetter(char firstLetter) {
        Session session = sessionFactory.openSession();
        List<Book> books = session.createQuery("FROM Book WHERE name LIKE :firstLetter")
                .setParameter("firstLetter", firstLetter +"%")
                .list();
        session.close();
        return books;
    }

    @Override
    public List<Book> sortByName(){
        return (List<Book>) sessionFactory.openSession().createQuery("FROM Book b ORDER BY b.name").list();
    }

    public List<Book> sortByYear(){
        return (List<Book>) sessionFactory.openSession().createQuery("FROM Book b ORDER BY b.year_of_publishing DESC").list();
    }

    public List<Book> sortByNumberOfPages(){
        return (List<Book>) sessionFactory.openSession().createQuery("FROM Book b ORDER BY b.number_of_pages DESC").list();
    }

    public List<Book> findByAuthor(String authorName) {
        Session session = sessionFactory.openSession();
        List<Book> books = session.createQuery("FROM Book b WHERE b.author.name = :name")
                .setParameter("name", authorName)
                .list();
        session.close();
        return books;
    }

    public List<Book> findByGenre(String genre) {
        Session session = sessionFactory.openSession();
        List<Book> books = session.createQuery("FROM Book WHERE genre = :genre")
                .setParameter("genre", genre)
                .list();
        session.close();
        return books;
    }

    public List<Book> findByPublisher(String publisherName) {
        Session session = sessionFactory.openSession();
        List<Book> books = session.createQuery("FROM Book b WHERE b.publisher.name = :name")
                .setParameter("name", publisherName)
                .list();
        session.close();
        return books;
    }

    public List<Book> findByNumberOfPages(String term, int numberOfPages) {
        Session session = sessionFactory.openSession();
        return switch (term) {
            case ">" ->
                    (List<Book>) session.createQuery("FROM Book WHERE number_of_pages >= :number_of_pages")
                            .setParameter("number_of_pages", numberOfPages)
                            .list();
            case "<" ->
                    (List<Book>) session.createQuery("FROM Book WHERE number_of_pages <= :number_of_pages")
                            .setParameter("number_of_pages", numberOfPages)
                            .list();
            default -> null;
        };
    }

    public List<Book> findByYear(String term, int year) {
        Session session = sessionFactory.openSession();
        return switch (term) {
            case ">" ->
                    (List<Book>) session.createQuery("FROM Book WHERE year_of_publishing >= :year_of_publishing")
                            .setParameter("year_of_publishing", year)
                            .list();
            case "<" ->
                    (List<Book>) session.createQuery("FROM Book WHERE year_of_publishing <= :year_of_publishing")
                            .setParameter("year_of_publishing", year)
                            .list();
            default -> null;
        };
    }

    @Override
    public List<Book> findAll() {
        Session session = sessionFactory.openSession();
        return (List<Book>) session.createQuery("From Book").list();
    }
}


