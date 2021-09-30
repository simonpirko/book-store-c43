package by.bookstore.servlets;

import by.bookstore.entity.User;
import by.bookstore.service.BookBasket;
import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddReservedBookServlet", urlPatterns = "/addReservedBook")
public class AddReservedBookServlet extends HttpServlet {
    private final FacadeService facade = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long bookId = Long.parseLong(req.getParameter("book_id"));
        BookBasket bookBasket = (BookBasket) req.getSession().getAttribute("basket");
        if(facade.addBookInBasket(bookId, bookBasket)){
            req.setAttribute("message_add_in_basket", "Book added to basket!");
        }else{
            req.setAttribute("message_add_in_basket", "Operation failed!");
        }
        resp.sendRedirect("/bookStore");
    }
}
