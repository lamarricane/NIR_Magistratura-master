package servlet;

import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet("/benchmark")
public class BookServletBenchmark extends HttpServlet {
    SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long totalTime = 0;
        int numOfRequests = 100;

        BookServlet bookServlet = new BookServlet();

        for (int i = 0; i < numOfRequests; i++) {
            long startTime = System.nanoTime();

            bookServlet.doGet(null, null);
            bookServlet.doPost(null, null);

            long endTime = System.nanoTime();
            long duration = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
            totalTime += duration;

            System.out.println("Request " + (i + 1) + " took: " + duration + " milliseconds");
        }

        long averageTime = totalTime / numOfRequests;

        resp.getWriter().println("Total time for " + numOfRequests + " requests: " + totalTime + " milliseconds");
        resp.getWriter().println("Average time per request: " + averageTime + " milliseconds");
    }
}