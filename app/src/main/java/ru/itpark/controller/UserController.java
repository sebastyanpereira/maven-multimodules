package ru.itpark.controller;

import lombok.RequiredArgsConstructor;
import model.User;
import service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl service;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List list = doSearch("");
            req.setAttribute("users", list);
            req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        service.save(new User("", name,  surname));
        resp.sendRedirect("/");
    }

    public List doSearch(String name) throws SQLException {
        return service.getAll();
    }
}
