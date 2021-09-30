package by.bookstore.servlets;

import by.bookstore.entity.Book;
import by.bookstore.entity.User;
import by.bookstore.service.BookBasket;
import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserReservedBooksServlet", urlPatterns = "/userReservedBooks")
public class UserReservedBooksServlet extends HttpServlet {
    private final FacadeService facade = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookBasket bookBasket = (BookBasket) req.getSession().getAttribute("basket");
        List<Book> reservedBooks = facade.getReservedBooks(bookBasket);
        req.setAttribute("reservedBooks", reservedBooks);
        req.getServletContext().getRequestDispatcher("/bookBasket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isBuy = Boolean.parseBoolean(req.getParameter("isBuy"));
        User user = (User) req.getSession().getAttribute("user");
        BookBasket bookBasket = (BookBasket) req.getSession().getAttribute("basket");
        if(isBuy){
            facade.confirmPurchase(user, bookBasket);
            req.setAttribute("message_purchase", "Items purchased successfully!");
        }
        req.getServletContext().getRequestDispatcher("/bookBasket.jsp").forward(req, resp);
    }
}
