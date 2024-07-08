package Controllers.Listeners;



import Controllers.Managers.QuizHistoryManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class QuizHistoryListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        QuizHistoryManager manager = new QuizHistoryManager();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(QuizHistoryManager.ATTRIBUTE_NAME, manager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(QuizHistoryManager.ATTRIBUTE_NAME);
    }
}
