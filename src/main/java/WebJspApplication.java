import dao.impl.*;
import model.*;
import service.*;
import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;

import java.time.LocalDate;
import java.util.List;

public class WebJspApplication {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        BookService bookService = new BookService(new BookDaoImpl(sessionFactory));
        AuthorService authorService = new AuthorService(new AuthorDaoImpl(sessionFactory));
        PublisherService publisherService = new PublisherService(new PublisherDaoImpl(sessionFactory));

        Author firstAuthor = new Author("Mary Stewart", LocalDate.of(1950, 3, 28));
        Author secondAuthor = new Author("Sarah Gio", LocalDate.of(1976, 8, 13));
        Author thirdAuthor = new Author("Rachel Caine", LocalDate.of(1980, 9, 9));
        Author fourthAuthor = new Author("Alisa Iskra", LocalDate.of(2001, 9, 28));
        Author fifthAuthor = new Author("Maria Karpova", LocalDate.of(2001, 7, 23));


        //bookService.multipleDeleteBook();
        //authorService.multipleDeleteAuthor();

        Publisher firstPublisher = new Publisher("Styria Pichler Verlag", "Austria");
        Publisher secondPublisher = new Publisher("Muenshen Edition Manufaktur", "German");
        Publisher thirdPublisher = new Publisher("Evro Book", "Serbia");

        /*Book firstBook = new Book("A fire in the night", "Detective", 322, 1990);
        Book secondBook = new Book("Abode of thorns", "Romance", 536, 1989);
        Book thirdBook = new Book("The last camellia", "Adventures", 298, 2004);
        Book fourthBook = new Book("Wolf River", "Detective", 450, 2013);
        Book fifthBook = new Book("Gloomy Bay", "Detective", 480, 2018);
        */

        /*authorService.saveAuthor(firstAuthor);
        authorService.saveAuthor(secondAuthor);
        authorService.saveAuthor(thirdAuthor);
        authorService.saveAuthor(fourthAuthor);
        authorService.saveAuthor(fifthAuthor);

         */



        /*publisherService.savePublisher(firstPublisher);
        publisherService.savePublisher(secondPublisher);
        publisherService.savePublisher(thirdPublisher);

         */



        /*firstBook.setAuthor(firstAuthor);
        secondBook.setAuthor(firstAuthor);
        thirdBook.setAuthor(secondAuthor);
        fourthBook.setAuthor(thirdAuthor);
        fifthBook.setAuthor(thirdAuthor);

        firstBook.setPublisher(firstPublisher);
        secondBook.setPublisher(secondPublisher);
        thirdBook.setPublisher(firstPublisher);
        fourthBook.setPublisher(thirdPublisher);
        fifthBook.setPublisher(thirdPublisher);

        firstAuthor.addBook(firstBook);
        firstAuthor.addBook(secondBook);
        secondAuthor.addBook(thirdBook);
        thirdAuthor.addBook(fourthBook);
        thirdAuthor.addBook(fifthBook);

        firstPublisher.addBook(firstBook);
        firstPublisher.addBook(thirdBook);
        secondPublisher.addBook(secondBook);
        thirdPublisher.addBook(fourthBook);
        thirdPublisher.addBook(fifthBook);

        bookService.saveBook(firstBook);
        bookService.saveBook(secondBook);
        bookService.saveBook(thirdBook);
        bookService.saveBook(fourthBook);
        bookService.saveBook(fifthBook);
        */

        /*List<Author> authorList = authorService.findAllAuthors();
        for (Author author : authorList) {
            System.out.println(author.toString());
        }

        List<Author> authorList = authorService.sortAuthorByName();
        for (Author author : authorList) {
            System.out.println(author.toString());
        }

        List<Author> authorList = authorService.findAuthorByFirstLetter('S');
        for (Author author : authorList) {
            System.out.println(author.toString());
        }
        */

        /*List<Publisher> publisherList = publisherService.findAllPublishers();
        for (Publisher publisher : publisherList) {
            System.out.println(publisher.toString());
        }

        List<Publisher> publisherList = publisherService.sortPublisherByName();
        for (Publisher publisher : publisherList) {
            System.out.println(publisher.toString());
        }

        List<Publisher> publisherList = publisherService.findPublisherByFirstLetter('E');
        for (Publisher publisher : publisherList) {
            System.out.println(publisher.toString());
        }

        List<Publisher> publisherList = publisherService.findPublisherByLocation("German");
        for (Publisher publisher : publisherList) {
            System.out.println(publisher.toString());
        }
        */

        /*List<Book> bookList = bookService.sortBookByName();
        for (Book book : bookList) {
            System.out.println(book.toString());
        }

         */

        /*List<Book> bookList = bookService.findAllBooks();
        for (Book book : bookList) {
            System.out.println(book.toString());
        }

        List<Book> bookList = bookService.findBookByAuthor("Sarah Gio");
        for (Book book : bookList) {
            System.out.println(book.toString());
        }

         List<Book> bookList = bookService.findBookByPublisher("Styria Pichler Verlag");
        for (Book book : bookList) {
            System.out.println(book.toString());
        }

        List<Book> bookList = bookService.findBookByGenre("Detective");
        for (Book book : bookList) {
            System.out.println(book.toString());
        }

        List<Book> bookList = bookService.findBookByFirstLetter('A');
        for (Book book : bookList) {
            System.out.println(book.toString());
        }
        */

        List<Book> bookList = bookService.findBookByYearOfPublishing("<", 1950);
        for (Book book : bookList) {
            System.out.println(book.toString());
        }

        /*List<Book> bookList = bookService.findBookByNumberOfPages("<", 450);
        for (Book book : bookList) {
            System.out.println(book.toString());
        }

         List<Book> bookList = bookService.sortBookByYear();
        for (Book book : bookList) {
            System.out.println(book.toString());
        }

         List<Book> bookList = bookService.sortBookByNumberOfPages();
        for (Book book : bookList) {
            System.out.println(book.toString());
        }
        */
    }
}














