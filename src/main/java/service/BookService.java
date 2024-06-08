package service;

import dao.impl.BookDaoImpl;
import model.Book;

import java.util.List;

public class BookService {
    private final BookDaoImpl bookDao;

    public BookService(BookDaoImpl bookDao) {
        this.bookDao = bookDao;
    }

    public Book findBookById(int id) {
        return bookDao.findById(id);
    }

    public void saveBook(Book book) {
        bookDao.save(book);
    }

    public void updateBook(Book book) {
        bookDao.update(book);
    }

    public void deleteBook(Book book) {
        bookDao.delete(book);
    }

    public List<Book> findBookByFirstLetter(String letter){
        return bookDao.findByFirstLetter(letter);
    }

    public List<Book> findBookByAuthor(String authorName){
        return bookDao.findByAuthor(authorName);
    }

    public List<Book> findBookByPublisher(String publisherName) {
        return bookDao.findByPublisher(publisherName);
    }

    public List<Book> findBookByNumberOfPages(String term, int numberOfPages) {
        return bookDao.findByNumberOfPages(term, numberOfPages);
    }

    public List<Book> findBookByYearOfPublishing(String term, int yearOfPublishing) {
        return bookDao.findByYearOfPublishing(term, yearOfPublishing);
    }

    public List<Book> findBookByGenre(String genre) {
        return bookDao.findByGenre(genre);
    }

    public List<Book> sortBookByName() {
        return bookDao.sortByName();
    }

    public List<Book> sortBookByYear() {
        return bookDao.sortByYear();
    }

    public List<Book> sortBookByNumberOfPages() {
        return bookDao.sortByNumberOfPages();
    }

    public List<Book> findAllBooks() {
        return bookDao.findAll();
    }

    public void multipleSaveBook(){
        bookDao.multipleSave();
    }

    public void multipleDeleteBook() {
        bookDao.multipleDelete();
    }
}