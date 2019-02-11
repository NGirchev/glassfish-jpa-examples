package ru.girchev.glassfishjpaexamples;

import ru.girchev.glassfishjpaexamples.service.UserBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Girchev N.A.
 * Date: 11.02.2019
 */
@WebServlet("/delete")
public class DeleteUser extends HttpServlet {

    @EJB
    private UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if(req.getParameter("id") != null && req.getParameter("id") != ""){
            long id = Long.valueOf(req.getParameter("id"));
            userBean.delete(id);
        }
        resp.sendRedirect("list");
    }
}
