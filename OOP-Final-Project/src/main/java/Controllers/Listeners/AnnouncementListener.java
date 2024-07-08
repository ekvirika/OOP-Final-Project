package Controllers.Listeners;

import Controllers.Managers.AnnouncementManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AnnouncementListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        AnnouncementManager announcementManager = new AnnouncementManager();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(AnnouncementManager.ATTRIBUTE_NAME, announcementManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(AnnouncementManager.ATTRIBUTE_NAME);
    }
}
