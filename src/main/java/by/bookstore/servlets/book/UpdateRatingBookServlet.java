package by.bookstore.servlets.book;

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

@WebServlet(name = "UpdateRatingBookServlet", urlPatterns = "/updRatingBook")
public class UpdateRatingBookServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(UpdateRatingBookServlet.class.getSimpleName());
    private final FacadeService facade = new FacadeService();
    private long bookId = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookId = Long.parseLong(req.getParameter("bookId"));
        req.getServletContext().getRequestDispatcher("/book/ratingBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("score") != null){
            int newAssessment = Integer.parseInt(req.getParameter("score"));
            Connection connection = (Connection) req.getSession().getAttribute("connection");
            if(newAssessment <= 5 && newAssessment >= 1){
                if(facade.updateRatingBook(bookId, newAssessment, connection)){
                    req.setAttribute("message_upd_rating", "Thank you for your feedback!");
                    logger.info("Add new score for book (id = {})", bookId);
                }else{
                    req.setAttribute("message_upd_rating", "Operation failed");
                }
            }else{
                req.setAttribute("message_upd_rating", "Score should be between 1 and 5!");
            }
        }
        req.getServletContext().getRequestDispatcher("/book/ratingBook.jsp").forward(req, resp);
    }
}
