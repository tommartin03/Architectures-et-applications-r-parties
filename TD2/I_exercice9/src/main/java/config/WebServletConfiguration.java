package config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import servlets.Exercice9Servlet;

public class WebServletConfiguration implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webctx=new AnnotationConfigWebApplicationContext();
        webctx.register(ClientWebConfig.class);
        webctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet=servletContext.addServlet("controleur",new Exercice9Servlet());
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(webctx);
        servletContext.addListener(contextLoaderListener);

    }
}
