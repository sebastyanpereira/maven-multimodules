package ru.itpark.container;

import ru.itpark.servlet.FrontController;

import javax.servlet.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class FrontControllerServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic registration = servletContext.addServlet("front-controller", new FrontController());
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
        try {
            Path temp = Files.createTempDirectory("tmp");
            registration.setMultipartConfig(new MultipartConfigElement(temp.toAbsolutePath().toString(), 10 * 1024 * 1024, 10 * 1024 * 1024, 1024 * 1024));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
