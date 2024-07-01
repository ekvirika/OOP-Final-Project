<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Quiz" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="https://cdn.dribbble.com/users/3975278/screenshots/17581195/media/2bd56e0a2606bfdf3c11edb74875658c.png?resize=800x600&vertical=center">
    <title>Quiz Description</title>
    <link rel="stylesheet" href="./css/QuizDescription.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
    <link rel="icon" href="./assets/Logo.png">
</head>
<body>
<% Quiz quiz = (Quiz) request.getAttribute("currentQuiz"); %>
<header>
    <h1><%= quiz.getQuizName() %></h1>
</header>

<div class="container">
    <div class="description">
        <h2>Quiz Description</h2>
        <p><span>Description:</span> <%= quiz.getQuizDescription() %></p>
        <p><span>Score:</span> <%= quiz.getQuizScore() %></p>
        <p><span>Question number:</span> <%= quiz.getQuestionIds().size() %></p>
        <p><span>Author:</span> <%= quiz.getCreatorUsername() %></p>
        <p><span>Page Type:</span> <%= quiz.isSinglePage() ? "Single page" : "Multiple pages" %></p>
        <p><span>Randomize Questions:</span> <%= quiz.isRandomizeQuestions() %></p>
        <p><span>Immediate Feedback:</span> <%= quiz.isImmediateFeedback() %></p>
        <p><span>Create Time:</span> <%= quiz.getCreateTime() %></p>
    </div>
    <div class="leaderboard">
        <h2>Leaderboard</h2>
    </div>
</div>

<form action="QuizServlet" method="post">
    <input type="hidden" name="quizId" value="<%= quiz.getQuizID() %>">
    <button type="submit" class="start-btn">Start Quiz</button>
</form>

</body>
</html>
