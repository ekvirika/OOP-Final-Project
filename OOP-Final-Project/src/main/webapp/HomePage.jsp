<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.LeaderboardEntry" %>
<%@ page import="Models.Quiz" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" type="text/css" href="./css/HomePage.css">
    <link rel="stylesheet" href="./css/StartPage.css">
    <link rel="stylesheet" href="./css/NavBar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
</head>
<body>
<!-- Navigation Bar -->
<header class="animate__animated animate__fadeInDown">
    <div class="logo-area">
        <img src="./assets/Logo.png" alt="Logo">
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
    <h1>Welcome to the Quizzz.com, <%= request.getAttribute("username") %>!</h1>
    <!-- Leaderboard -->
    <section>
        <h2>Leaderboard</h2>
        <table>
            <thead>
                <tr>
                    <th>Rank</th>
                    <th>User</th>
                    <th>Score</th>
                </tr>
            </thead>
            <tbody>
            <% List<LeaderboardEntry> leaderboard = (List<LeaderboardEntry>) request.getAttribute("leaderboard");
                for (LeaderboardEntry user : leaderboard) { %>
            <tr>
                <td><%= user.getRank() %></td>
                <td><%= user.getUsername() %></td>
                <td><%= user.getScore() %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </section>

    <!-- New Quizzes -->
    <section>
        <h2>New Quizzes</h2>
        <ul class="quiz-list">
            <%
                List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes");
                for (Quiz quiz : quizzes) {
            %>
            <li class="quiz-item">
                <h2><a href="QuizServlet?quizId=<%= quiz.getQuizID() %>"><%= quiz.getQuizName() %></a></h2>
                <!-- <button onclick="window.location.href='QuizServlet?quizId=<%= quiz.getQuizID() %>'">Start Quiz</button> -->
            </li>
            <% } %>
        </ul>
    </section>
</div>

<footer>
    <p>&copy; 2024 Quiz Website. All rights reserved.</p>
</footer>
</body>
</html>
