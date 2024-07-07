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
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="./css/HomePage.css">
    <link rel="stylesheet" href="./css/StartPage.css">
    <link rel="stylesheet" href="./css/NavBar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

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

<h1>Welcome to the Quizzz.com, <%= request.getAttribute("username") %>!</h1>
<div class="search-container">
    <form id="search-form" class="search-form" action="SuggestionServlet" method="get">
        <input id="search-input" class="search-input" type="text" name="query" placeholder="Search A Friend...">
        <button class="search-button" type="submit">
            <i class="fa fa-search"></i>
        </button>
        <div id="suggestions" class="suggestions-container"></div>
    </form>
</div>


<div class="container">
    <form action="CreateQuizServlet" method="get">
        <button class="button-5" role="button">Create Quiz</button>
    </form>


    <!-- New Quizzes -->
    <section>
        <h2>Explore Quizzes</h2>
        <ul class="quiz-list">
            <%
                List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes");
                for (Quiz quiz : quizzes) {
            %>
            <li class="quiz-item">
                <h2><a href="QuizServlet?quizId=<%= quiz.getQuizID() %>"><%= quiz.getQuizName() %>
                </a></h2>
            </li>
            <% } %>
        </ul>
    </section>
</div>
<script src="javascript/SearchBar.js" defer></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const searchContainer = document.querySelector('.search-container');
        const searchInput = document.getElementById('search-input');

        searchInput.addEventListener('focus', function () {
            searchContainer.classList.add('expanded');
        });

        searchInput.addEventListener('blur', function () {
            if (searchInput.value.trim() === '') {
                searchContainer.classList.remove('expanded');
            }
        });
    });

</script>
</body>
</html>