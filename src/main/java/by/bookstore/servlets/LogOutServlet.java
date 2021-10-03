package by.bookstore.servlets;

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

@WebServlet(name = "LogOutServlet", urlPatterns = "/logOut")
public class LogOutServlet extends HttpServlet {
    private final FacadeService facade = new FacadeService();
    private final Logger logger = LoggerFactory.getLogger(LogOutServlet.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookBasket bookBasket = (BookBasket) req.getSession().getAttribute("basket");
        facade.resetReservedStatusBookAfterLogOut(bookBasket);
        logger.info("Logged out of the account.");
        req.getSession().invalidate();
        resp.sendRedirect("/main");
    }
}
