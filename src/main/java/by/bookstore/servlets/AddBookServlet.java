package by.bookstore.servlets;


import by.bookstore.entity.Book;
import by.bookstore.entity.User;
import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddBookServlet", urlPatterns = "/addBook")
public class AddBookServlet extends HttpServlet {
    private final FacadeService facadeService = new FacadeService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/saveBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        double rating = Double.parseDouble(req.getParameter("rating"));
        double price = Double.parseDouble(req.getParameter("price"));
        User user = (User) req.getSession().getAttribute("user");

        if (rating <= 5 && rating >= 1) {
            Book book = new Book(name, author, rating, price, false, new User(user.getId()));
            if (facadeService.addBook(book)) {
                req.setAttribute("message_add_book", "Book added");
            } else {
                req.setAttribute("message_add_book", "Book already exists!");
            }
        } else {
            req.setAttribute("message_add_book", "Score should be between 1 and 5!");
        }
        getServletContext().getRequestDispatcher("/saveBook.jsp").forward(req, resp);
    }
}
