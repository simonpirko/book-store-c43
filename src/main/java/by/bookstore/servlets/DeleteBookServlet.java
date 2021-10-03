package by.bookstore.servlets;

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

@WebServlet(name = "DeleteBookServlet", urlPatterns = "/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    private final FacadeService facade = new FacadeService();
    private final Logger logger = LoggerFactory.getLogger(DeleteBookServlet.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long bookId = Long.parseLong(req.getParameter("book_id"));
        User user = (User) req.getSession().getAttribute("user");

        if(facade.deleteBook(user, bookId)){
            req.setAttribute("message_remove_Book", "Book has been deleted!");
            logger.info("Successful deletion of a book(id = {}.", bookId);
        }else{
            req.setAttribute("message_remove_Book", "Operation failed!");
        }
        resp.sendRedirect("/bookStore");
    }
}
