package by.bookstore.servlets.book;

import by.bookstore.entity.Book;
import by.bookstore.entity.User;
import by.bookstore.service.FacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "UpdateBookInfoServlet", urlPatterns = "/updBook")
public class UpdateBookInfoServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(UpdateBookInfoServlet.class.getSimpleName());
    private final FacadeService facade = new FacadeService();
    private long idToUpdate = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        idToUpdate = Long.parseLong(req.getParameter("idBook"));
        req.getServletContext().getRequestDispatcher("/book/updateBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        double rating = Double.parseDouble(req.getParameter("rating"));
        double price = Double.parseDouble(req.getParameter("price"));

        User user = (User) req.getSession().getAttribute("user");
        Connection connection = (Connection) req.getSession().getAttribute("connection");

        if(facade.updateBook(user, new Book(idToUpdate, name, author, rating, price, false, new User(user.getId())), connection)){
            req.setAttribute("message_upd_book", "Operation has been successfully!");
            logger.info("Update book with id = {}", idToUpdate);
        }else{
            req.setAttribute("message_upd_book", "Operation failed!");
        }
        req.getServletContext().getRequestDispatcher("/book/updateBook.jsp").forward(req, resp);
    }
}
