package by.bookstore.servlets.book;

import by.bookstore.entity.Book;
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
import java.util.List;

@WebServlet(name = "UserReservedBooksServlet", urlPatterns = "/userReservedBooks")
public class UserReservedBooksServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(UserReservedBooksServlet.class.getSimpleName());
    private final FacadeService facade = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookBasket bookBasket = (BookBasket) req.getSession().getAttribute("basket");
        Connection connection = (Connection) req.getSession().getAttribute("connection");

        List<Book> reservedBooks = facade.getReservedBooks(bookBasket, connection);
        req.setAttribute("reservedBooks", reservedBooks);
        req.getServletContext().getRequestDispatcher("/book/bookBasket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isBuy = Boolean.parseBoolean(req.getParameter("isBuy"));
        User user = (User) req.getSession().getAttribute("user");
        BookBasket bookBasket = (BookBasket) req.getSession().getAttribute("basket");
        Connection connection = (Connection) req.getSession().getAttribute("connection");

        if(isBuy){
            facade.confirmPurchase(user, bookBasket, connection);
            req.setAttribute("message_purchase", "Items purchased successfully!");
            logger.info("Successful book shopping for user: {}", user.getName());
        }
        req.getServletContext().getRequestDispatcher("/book/bookBasket.jsp").forward(req, resp);
    }
}
