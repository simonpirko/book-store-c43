package by.bookstore.servlets;

import by.bookstore.entity.Book;
import by.bookstore.service.BookListHandlerService;
import by.bookstore.service.Dependencies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookStoreServlet", urlPatterns = "/bookStore")
public class BookStoreServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(BookStoreServlet.class.getSimpleName());
    private int currentPage = 1;
    private final int numValuesPage = 4;
    private int numPages = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        currentPage = 1;
        if (req.getParameter("currentPage") != null) {
            currentPage = Integer.parseInt(req.getParameter("currentPage"));
        }
        List<Book> currentList = Dependencies.bookListHandlerService.getCurrentPage(currentPage,numValuesPage);
        numPages = Dependencies.bookListHandlerService.getNumPagesForResponse();
        req.setAttribute("currentList", currentList);
        req.setAttribute("numPages", numPages);
        req.setAttribute("currentPage", currentPage);

        logger.info("Request for all books.");
        getServletContext().getRequestDispatcher("/bookStore.jsp").forward(req, resp);
    }
}
