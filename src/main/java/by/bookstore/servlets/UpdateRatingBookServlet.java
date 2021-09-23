package by.bookstore.servlets;

import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class UpdateRatingBookServlet extends HttpServlet {
    private final FacadeService facade = new FacadeService();
    private long bookId = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookId = Long.parseLong(req.getParameter("bookId"));
        req.getServletContext().getRequestDispatcher("").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int newAssessment = Integer.parseInt(req.getParameter("score"));

        if(facade.updateRatingBook(bookId, newAssessment)){
            req.setAttribute("message_upd_rating", "Thank you for your feedback!");
        }else{
            req.setAttribute("message_upd_rating", "Operation failed");
        }
        req.getServletContext().getRequestDispatcher("").forward(req, resp);
    }
}
