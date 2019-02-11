package ru.girchev.glassfishjpaexamples;

import ru.girchev.glassfishjpaexamples.domain.User;
import ru.girchev.glassfishjpaexamples.service.UserBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Girchev N.A.
 * Date: 11.02.2019
 */
@WebServlet("/list")
public class MainServlet extends HttpServlet{

    // Аннотация говорит о том,
    // что данный объект будет инициализирован
    // контейнером Glassfish DI
    @EJB
    private UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Получаем список пользователей
        List<User> allUser = userBean.getAll();

        // добавляем полученный список в request,
        // который отправится на jsp
        req.setAttribute("users", allUser);

        // отправляем request на jsp
        req.getRequestDispatcher("/list.jsp").forward(req, resp);

    }

}
