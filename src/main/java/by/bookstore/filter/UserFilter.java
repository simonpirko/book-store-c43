package by.bookstore.filter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"AddBookServlet","AddCommentServlet", "AddLikeServlet", "AddReservedBookServlet",
        "BookCommentServlet","BookStoreServlet","DeleteBookServlet","DeleteCommentServlet","LogOutServlet",
        "UpdateBookInfoServlet","UpdateCommentServlet","UpdateNameUserServlet","UpdatePasswordUserServlet",
        "UpdateRatingBookServlet","UserAccountServlet","UserBookServlet","UserReservedBooksServlet"

} )
public class UserFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(req.getSession().getAttribute("user") != null){
            chain.doFilter(req, res);
        }else  res.sendRedirect("/main");
    }
}
