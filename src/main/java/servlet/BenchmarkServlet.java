package servlet;

import dao.impl.*;
import model.*;
import org.hibernate.SessionFactory;

import service.*;
import utils.HibernateSessionFactoryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/benchmark")
public class BenchmarkServlet extends HttpServlet {
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private List<Book> books;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BookService service = new BookService(new BookDaoImpl(sessionFactory));
            books = service.findAllBooks();
            req.setAttribute("books", books);
            getServletContext().getRequestDispatcher("/benchmark.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BookService bookService = new BookService(new BookDaoImpl(sessionFactory));

            String actionParam = req.getParameter("action");
            if (actionParam != null && !actionParam.isEmpty() && actionParam.equals("generateBookList")) {
                long startTime = System.currentTimeMillis();
                bookService.multipleSaveBook();
                long endTime = System.currentTimeMillis();
                long BookTime = endTime - startTime;
                System.out.println("Время выполнения метода: " + BookTime + " миллисекунд");
                resp.sendRedirect("/benchmark");
            }
            if (actionParam != null && !actionParam.isEmpty() && actionParam.equals("deleteBookList")) {
                bookService.multipleDeleteBook();
                resp.sendRedirect("/benchmark");
            }
            if (actionParam != null && !actionParam.isEmpty() && actionParam.equals("sortBookListByName")) {
                List<Book> bookList = bookService.sortBookByName();
                for (Book book : bookList) {
                    System.out.println(book.toString());
                }
                resp.sendRedirect("/benchmark");
            }
            if (actionParam != null && !actionParam.isEmpty() && actionParam.equals("sortBookListByYear")) {
                List<Book> bookList = bookService.sortBookByYear();
                for (Book book : bookList) {
                    System.out.println(book.toString());
                }
                resp.sendRedirect("/benchmark");
            }
            if (actionParam != null && !actionParam.isEmpty() && actionParam.equals("sortBookListByNumberOfPages")) {
                List<Book> bookList = bookService.sortBookByNumberOfPages();
                for (Book book : bookList) {
                    System.out.println(book.toString());
                }
                resp.sendRedirect("/benchmark");
            }
            if (!req.getParameterMap().isEmpty()) {
                if (!req.getParameter("findByFirstLetter").isEmpty()) {
                    if (actionParam != null && !actionParam.isEmpty() && actionParam.equals("findByFirstLetter")) {
                        List<Book> bookList = bookService.findBookByFirstLetter(req.getParameter("findByFirstLetter"));
                        for (Book book : bookList) {
                            System.out.println(book.toString());
                        }
                        resp.sendRedirect("/benchmark");
                    }
                }
                if (!req.getParameter("findByAuthor").isEmpty()) {
                    if (actionParam != null && !actionParam.isEmpty() && actionParam.equals("findByAuthor")) {
                        List<Book> bookList = bookService.findBookByAuthor(req.getParameter("findByAuthor"));
                        for (Book book : bookList) {
                            System.out.println(book.toString());
                        }
                        resp.sendRedirect("/benchmark");
                    }
                }
                if (!req.getParameter("findByPublisher").isEmpty()) {
                    if (actionParam != null && !actionParam.isEmpty() && actionParam.equals("findByPublisher")) {
                        List<Book> bookList = bookService.findBookByPublisher(req.getParameter("findByPublisher"));
                        for (Book book : bookList) {
                            System.out.println(book.toString());
                        }
                        resp.sendRedirect("/benchmark");
                    }
                }
                if (!req.getParameter("findByGenre").isEmpty()) {
                    if (actionParam != null && !actionParam.isEmpty() && actionParam.equals("findByGenre")) {
                        List<Book> bookList = bookService.findBookByGenre(req.getParameter("findByGenre"));
                        for (Book book : bookList) {
                            System.out.println(book.toString());
                        }
                        resp.sendRedirect("/benchmark");
                    }
                }
                if (!req.getParameter("findByNumberOfPages").isEmpty()) {
                    String term = req.getParameter("term");
                    int numberOfPages = Integer.parseInt(req.getParameter("findByNumberOfPages"));
                    List<Book> bookList = bookService.findBookByNumberOfPages(term, numberOfPages);
                    for (Book book : bookList) {
                        System.out.println(book.toString());
                    }
                    resp.sendRedirect("/benchmark");
                }

                if (!req.getParameter("findByYearOfPublishing").isEmpty()) {
                    String term = req.getParameter("term1");
                    int yearOfPublishing = Integer.parseInt(req.getParameter("findByYearOfPublishing"));
                    List<Book> bookList = bookService.findBookByYearOfPublishing(term, yearOfPublishing);
                    for (Book book : bookList) {
                        System.out.println(book.toString());
                    }
                    resp.sendRedirect("/benchmark");
                }
            }

        } catch (Exception exception) {
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            throw new RuntimeException(exception.getMessage());
        }
    }
}