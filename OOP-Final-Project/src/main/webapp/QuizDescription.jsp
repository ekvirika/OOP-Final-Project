<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>
<body>

<header>
    <h1>Quiz Description</h1>
</header>

<div class="description">
    <% Quiz quiz = (Quiz) request.getAttribute("currentQuiz"); %>
        <p>Quiz Name: <%= quiz.getQuizName() %></p>
    <p>Description: <%= quiz.getQuizDescription() %></p>
    <p>Score: <%= quiz.getQuizScore() %></p>
</div>

<button class="start-btn" onclick="location.href='quiz.html?quizId=<%= quiz.getQuizID() %>'">Start Quiz</button>

</body>
</html>
