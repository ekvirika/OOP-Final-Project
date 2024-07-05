package Controllers.Listeners;

import Models.LeaderboardEntry;
import Models.Managers.LeaderboardManager;
import Models.Managers.QuizManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LeaderboardListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LeaderboardManager manager = new LeaderboardManager();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(LeaderboardManager.ATTRIBUTE_NAME, manager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(LeaderboardManager.ATTRIBUTE_NAME);
    }
}
