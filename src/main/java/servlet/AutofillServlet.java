package servlet;

import com.google.gson.Gson;
import dao.impl.*;
import model.*;
import org.hibernate.SessionFactory;
import service.*;
import utils.HibernateSessionFactoryUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/autofill")
public class AutofillServlet extends HttpServlet {
    private final SessionFactory factory = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!req.getParameter("autofillAuthors").isEmpty()) {
            AuthorService service = new AuthorService(new AuthorDaoImpl(factory));
            List<Author> authors = service.findAllAuthors();
            List<String> names = new ArrayList<>();
            for (Author author : authors) {
                names.add(author.getName());
            }
            String json = new Gson().toJson(names);
            resp.getWriter().write(json);
        }
        if (!req.getParameter("autofillPublishers").isEmpty()) {
            PublisherService service = new PublisherService(new PublisherDaoImpl(factory));
            List<Publisher> publishers = service.findAllPublishers();
            List<String> names = new ArrayList<>();
            for (Publisher publisher : publishers) {
                names.add(publisher.getName());
            }
            String json = new Gson().toJson(names);
            resp.getWriter().write(json);
        }
        if (!req.getParameter("autofillBooks").isEmpty()) {
            BookService service = new BookService(new BookDaoImpl(factory));
            List<Book> books = service.findAllBooks();
            List<String> names = new ArrayList<>();
            for (Book book : books) {
                names.add(book.getName());
            }
            String json = new Gson().toJson(names);
            resp.getWriter().write(json);
        }
    }
}
