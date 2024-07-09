package Controllers.Listeners;
import Controllers.Managers.AccountManager;
import Controllers.Managers.AchievementManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AchievementListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        AchievementManager achievementManager = new AchievementManager();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(AchievementManager.ATTRIBUTE_NAME, achievementManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(AchievementManager.ATTRIBUTE_NAME);
    }
}
