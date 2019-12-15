package ru.itpark.router;


import lombok.val;
import repository.UserRepositoryImpl;
import ru.itpark.controller.UserController;
import ru.itpark.exception.InitializationException;
import ru.itpark.exception.NotFoundException;
import service.UserServiceImpl;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

public class DefaultRouterImpl implements Router {
    private UserController userController;

    @Override
    public void init() {
        final InitialContext context;
        try {
            context = new InitialContext();
            final DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/db");
            userController = new UserController(new UserServiceImpl(new UserRepositoryImpl(ds)));
        } catch (NamingException e) {
            throw new InitializationException(e);
        }
    }

    @Override
    public void route(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            final String url = request.getRequestURI().substring(request.getContextPath().length());
            if (url.equals("/")) {
                String method = request.getMethod().toUpperCase();
                switch (method) {
                    case "GET":
                        userController.doGet(request, response);
                        break;
                    case "POST":
                        userController.doPost(request, response);
                        break;
                }
            } else if (url.startsWith("/search")) {
                userController.getSearch(request, response);
            }
        } catch (Exception e) {
            try {
                request.getRequestDispatcher("/WEB-INF/404.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
