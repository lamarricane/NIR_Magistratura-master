package dao.impl;

import dao.Dao;
import model.Author;
import model.Book;
import model.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.AuthorService;
import service.PublisherService;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void multipleSave() {
        try (Session session = sessionFactory.openSession()) {
            for (int i = 0; i < 10; i++) {
                Transaction transaction = session.beginTransaction();
                Book book = generate();
                session.save(book);
                transaction.commit();
            }
            session.close();
        }
    }

    public Book generate() {
        try (Session session = sessionFactory.openSession()) {

            char[] alphabet = new char[26];
            for (char ch = 'a'; ch <= 'z'; ch++) {
                alphabet[ch - 'a'] = ch;
            }

            String[] genreArray = {"Detective", "Fiction", "Mystery", "Romance", "Thriller", "Fantasy", "Historical", "Horror"};
            Random random = new Random();
            AuthorService authorService = new AuthorService(new AuthorDaoImpl(sessionFactory));
            var authors = authorService.findAllAuthors();
            PublisherService publisherService = new PublisherService(new PublisherDaoImpl(sessionFactory));
            var publishers = publisherService.findAllPublishers();
            Transaction transaction = session.beginTransaction();

            StringBuilder nameBuild = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                nameBuild.append(alphabet[random.nextInt(0, 26)]);
            }
            String name = String.valueOf(nameBuild);
            String genre = genreArray[random.nextInt(0, 7)];
            int numberOfPages = random.nextInt(0, 1000);
            int yearOfPublishing = random.nextInt(1930, 2025);
            Book book = new Book(name, genre, numberOfPages, yearOfPublishing);
            Author someAuthor = authors.get(random.nextInt(0, authors.size()));
            Publisher somePublisher = publishers.get(random.nextInt(0, publishers.size()));
            book.setAuthor(someAuthor);
            book.setPublisher(somePublisher);
            session.close();
            return book;
        }
    }

    @Override
    public List<Book> findByFirstLetter(String firstLetter) {
        Session session = sessionFactory.openSession();
        List<Book> books = session.createQuery("FROM Book WHERE name LIKE :firstLetter")
                .setParameter("firstLetter", firstLetter + "%")
                .list();
        session.close();
        return books;
    }

    @Override
    public List<Book> sortByName() {
        return (List<Book>) sessionFactory.openSession().createQuery("FROM Book b ORDER BY b.name").list();
    }

    public List<Book> sortByYear() {
        return (List<Book>) sessionFactory.openSession().createQuery("FROM Book b ORDER BY b.year_of_publishing DESC").list();
    }

    public List<Book> sortByNumberOfPages() {
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
            case ">" -> (List<Book>) session.createQuery("FROM Book WHERE number_of_pages >= :number_of_pages")
                    .setParameter("number_of_pages", numberOfPages)
                    .list();
            case "<" -> (List<Book>) session.createQuery("FROM Book WHERE number_of_pages <= :number_of_pages")
                    .setParameter("number_of_pages", numberOfPages)
                    .list();
            default -> null;
        };
    }

    public List<Book> findByYearOfPublishing(String term, int yearOfPublishing) {
        Session session = sessionFactory.openSession();
        return switch (term) {
            case ">" -> (List<Book>) session.createQuery("FROM Book WHERE year_of_publishing >= :year_of_publishing")
                    .setParameter("year_of_publishing", yearOfPublishing)
                    .list();
            case "<" -> (List<Book>) session.createQuery("FROM Book WHERE year_of_publishing <= :year_of_publishing")
                    .setParameter("year_of_publishing", yearOfPublishing)
                    .list();
            default -> null;
        };
    }

    @Override
    public List<Book> findAll() {
        Session session = sessionFactory.openSession();
        return (List<Book>) session.createQuery("From Book").list();
    }

    public void multipleDelete() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            List<Book> books = findAll();

            for (Book book : books) {
                session.createQuery("DELETE FROM Book b WHERE b.id = :id")
                        .setParameter("id", book.getId())
                        .executeUpdate();
            }
            transaction.commit();
        }
    }
}


