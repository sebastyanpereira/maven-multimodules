package ru.itpark.servlet;

import ru.itpark.router.Router;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FrontController extends HttpServlet {
    private Router router;

    @Override
    public void init() {

        final List<Router> routes = StreamSupport.stream(ServiceLoader.load(Router.class).spliterator(), false).collect(Collectors.toList());

        if (routes.isEmpty()) {
            throw new RuntimeException("No router found");
        }

        if (routes.size() != 1) {
            throw new RuntimeException("Multiple routers found");
        }

        router = routes.get(0);
        router.init();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        router.route(req, resp);
    }
}