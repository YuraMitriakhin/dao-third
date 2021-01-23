package com.gmail.yuramitryahin.controller;

import com.gmail.yuramitryahin.exception.AuthenticationException;
import com.gmail.yuramitryahin.lib.Injector;
import com.gmail.yuramitryahin.modal.Driver;
import com.gmail.yuramitryahin.security.AuthenticationService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
    private static final String DRIVER_ID = "driver_id";
    private static final Injector injector = Injector.getInstance("com.gmail.yuramitryahin");
    private AuthenticationService authenticationService = (AuthenticationService) injector
            .getInstance(AuthenticationService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("driver_login");
        String password = req.getParameter("driver_password");

        try {
            Driver driver = authenticationService.login(login, password);
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute(DRIVER_ID, driver.getId());
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (AuthenticationException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
