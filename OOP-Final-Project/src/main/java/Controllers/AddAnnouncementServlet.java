package Controllers;

import Controllers.Managers.AnnouncementManager;
import Models.Announcement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddAnnouncementServlet", urlPatterns = {"/AddAnnouncementServlet"})
public class AddAnnouncementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.getRequestDispatcher("AddAnnouncement.jsp").forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String announcementText = request.getParameter("announcement");
        String username = request.getSession().getAttribute("username").toString();
        AnnouncementManager announcementManager = (AnnouncementManager) request.getServletContext().getAttribute(AnnouncementManager.ATTRIBUTE_NAME);
        Announcement announcement = new Announcement(username, announcementText);
        announcementManager.createAnnouncement(announcement);
        System.out.println("Announcement Text: " + announcementText);

        response.sendRedirect("HomePageServlet");
    }
}
