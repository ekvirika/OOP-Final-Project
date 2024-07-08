package Controllers.Listeners;

import Models.Managers.AccountManager;
import Models.Managers.NotificationManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class NotificationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        NotificationManager notification = new NotificationManager();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(NotificationManager.ATTRIBUTE_NAME, notification);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(NotificationManager.ATTRIBUTE_NAME);
    }
}
