package Controllers.Listeners;

import Controllers.Managers.FriendManager;
import Controllers.Managers.NotificationManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class FriendRequestListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        FriendManager friendManager = new FriendManager();
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(FriendManager.ATTRIBUTE_NAME, friendManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute(FriendManager.ATTRIBUTE_NAME);
    }
}