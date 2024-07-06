<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Account" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/ProfilePage.css">
    <link rel="stylesheet" href="./css/NavBar.css">
    <title>User Profile</title>
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
</head>
<body>
<header class="animate__animated">
    <div class="logo-area">
        <a href="/HomePageServlet">
            <img src="./assets/Logo.png" alt="Logo">
        </a>
        <span class="website-name">Q<span class="u">u</span><span class="i">i</span>zzz</span>
    </div>
    <div class="nav-bar">
        <ul>
            <li><a href="/HomePageServlet">Quizzies</a></li>
            <li><a href="/ProfileServlet">Profile</a></li>
            <li><a href="/LogoutServlet">Logout</a></li>
        </ul>
    </div>
</header>

<div class="container">
    <div class="top">
        <div class="profile-picture-container animate__animated animate__jackInTheBox">
            <img src="<%= ((Account) request.getAttribute("account")).getImageUrl() %>" alt="Profile Picture"
                 class="profile-picture">
        </div>
        <div class="profile-info selected animate__animated animate__fadeIn">
            <h2>User Profile</h2>
            <p>
                <strong>Name:</strong> <span class="val"><%= ((Account) request.getAttribute("account")).getFirstName() %></span>
            </p>
            <p>
                <strong>Last name:</strong> <span class="val"><%= ((Account) request.getAttribute("account")).getLastName() %></span>
            </p>
            <p>
                <strong>Username:</strong> <span class="val"><%= ((Account) request.getAttribute("account")).getUserName() %></span>
            </p>
            <p>
                <strong>Email:</strong> <span class="val"><%= ((Account) request.getAttribute("account")).getEmail() %></span>
            </p>
            <% Boolean isSelf = (Boolean) request.getAttribute("isSelf"); %>
            <% if (isSelf != null && isSelf) { %>
            <form action="ProfileServlet" method="post">
                <button type="submit" name="action" value="editProfile" class="submit">Edit Profile</button>
            </form>
            <% } else { %>
            <button type="button" class="submit" >Send Note</button>
            <button type="button" class="submit" >Challenge</button>
            <button type="submit" class="submit">Add Friend</button>
            <% } %>
        </div>

        <div class="achievements animate__animated animate__fadeInRight">
            <h3>Achievements</h3>
            <p>No achievements yet.</p>
        </div>
    </div>
    <div class="bottom animate__animated animate__fadeInUp">
        <div class="quizzes">
            <h3>Quizzes Created</h3>
            <p>No quizzes created yet.</p>
<%--             TODO--%>
        </div>

        <div class="friends">
            <h3>Friends</h3>
<%--            TODO --%>
            <%-- Display list of friends if needed --%>
        </div>
    </div>
</div>

</body>
</html>
