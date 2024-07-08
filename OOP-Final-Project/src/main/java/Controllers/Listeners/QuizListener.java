package Controllers.Listeners;
import Controllers.Managers.QuizManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class QuizListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        QuizManager quizManager = new QuizManager();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(QuizManager.ATTRIBUTE_NAME, quizManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(QuizManager.ATTRIBUTE_NAME);
    }
}
