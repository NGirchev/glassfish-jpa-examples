package ru.girchev.glassfishjpaexamples.servlets;

import ru.girchev.glassfishjpaexamples.service.MainService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


/**
 * @author Girchev N.A.
 * Date: 11.02.2019
 */
@WebServlet("/main")
public class MainTestServlet extends HttpServlet {

    @Inject
    @Named("mainService")
    private MainService mainService;

    @Inject
    private Logger logger;

    public void main() {
        mainService.main();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        main();
    }
}
