package by.bookstore.servlets.book;

import by.bookstore.entity.User;
import by.bookstore.service.BookBasket;
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

@WebServlet(name = "AddReservedBookServlet", urlPatterns = "/addReservedBook")
public class AddReservedBookServlet extends HttpServlet {
    private final FacadeService facade = new FacadeService();
    private final Logger logger = LoggerFactory.getLogger(AddReservedBookServlet.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long bookId = Long.parseLong(req.getParameter("book_id"));
        BookBasket bookBasket = (BookBasket) req.getSession().getAttribute("basket");

        User user = (User) req.getSession().getAttribute("user");
        Connection connection = (Connection) req.getSession().getAttribute("connection");

        if(facade.addBookInBasket(bookId, bookBasket, connection)){
            req.setAttribute("message_add_in_basket", "Book added to basket!");
            logger.info("{} reserved book", user.getName());
        }else{
            req.setAttribute("message_add_in_basket", "Operation failed!");
        }
        resp.sendRedirect("/bookStore");
    }
}
