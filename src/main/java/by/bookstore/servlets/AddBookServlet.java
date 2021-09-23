package by.bookstore.servlets;


import by.bookstore.entity.Book;
import by.bookstore.entity.Comment;
import by.bookstore.entity.Like;
import by.bookstore.entity.User;
import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name="AddBookServlet", urlPatterns = "")
public class AddBookServlet extends HttpServlet {
    private final FacadeService  facadeService = new FacadeService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("").forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("name");
       String author = req.getParameter("author");
       double rating = Double.parseDouble("rating");
       double price = Double.parseDouble("price");
       User user = (User)req.getSession().getAttribute("user");

       Book book = new Book(name, author, rating, price, false, new User(user.getId()));
       if(facadeService.addBook(book)){
           req.setAttribute("message_add_book", "Book added");
       } else {
           req.setAttribute("message_add_book","Book already exists!");
       }
        getServletContext().getRequestDispatcher("").forward(req, resp);
       }

    }
