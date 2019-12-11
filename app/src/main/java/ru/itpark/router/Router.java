package ru.itpark.router;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@FunctionalInterface
public interface Router {
    default void init() {};
    void route(HttpServletRequest request, HttpServletResponse response);
}
