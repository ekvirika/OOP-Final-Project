<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Account" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="./css/ProfilePage.css">
    <link rel="stylesheet" href="./css/NavBar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
</head>
<body class="animate__animated animate__fadeInDown">
<header class="animate__animated animate__fadeInDown">
    <div class="logo-area">
        <img src="./assets/Logo.png" alt="Logo">
        <span class="website-name">Q<span class="u">u</span><span class="i">i</span>zzz</span>
    </div>
    <div class="nav-bar">
        <ul>
            <li><a href="/HomePageServlet">Quizzies</a></li>
            <li><a href="/ProfileServlet">Profile</a></li>
            <li><a href="/">Logout</a></li>
        </ul>
    </div>
</header>

<div class="container">
    <form action="/ProfileServlet" method="post" enctype="multipart/form-data">
        <div class="profile-picture-container">
            <img src="<%= ((Account) request.getAttribute("account")).getImageUrl() %>" alt="Profile Picture"
                 class="profile-picture">
            <input type="file" name="image" accept="image/*">
        </div>
        <div class="profile-info">
            <h2>User Profile</h2>
            <p><strong>Username:</strong> <%= ((Account) request.getAttribute("account")).getUserName() %></p>
            <p><strong>Name:</strong>
                <input type="text" name="firstName" value="<%= ((Account) request.getAttribute("account")).getFirstName() %>"/>
                <input type="text" name="lastName" value="<%= ((Account) request.getAttribute("account")).getLastName() %>"/>
            </p>
            <p><strong>Email:</strong> <input type="email" name="email" value="<%= ((Account) request.getAttribute("account")).getEmail() %>"/></p>
        </div>

        <div class="achievements">
            <h3>Achievements</h3>
            <p>No achievements yet.</p>
        </div>

        <div class="quizzes">
            <h3>Quizzes Created</h3>
            <p>No quizzes created yet.</p>
        </div>

        <div class="friends">
            <h3>Friends</h3>
            <ul>
                <c:forEach var="friend" items="${account.friends}">
                    <li>${friend}</li>
                </c:forEach>
            </ul>
        </div>

        <input type="submit" class="submit" value="Update Profile">
    </form>
</div>

</body>
</html>
