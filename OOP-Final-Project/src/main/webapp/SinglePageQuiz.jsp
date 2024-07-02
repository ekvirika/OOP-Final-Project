<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Quiz, java.util.Collections, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Single Page Quiz</title>
    <link rel="stylesheet" href="./css/QuizPage.css">
    <!-- Add your CSS links and any other meta tags or scripts -->
</head>
<body>
    <%-- Retrieve quiz and questions from database --%>
    <% Quiz quiz = fetchQuizFromDatabase(request.getParameter("quizId")); %>
    <% List<Integer> questionIds = new ArrayList<>(quiz.getQuestionIds()); %>
    
    <%-- Display one question at a time --%>
    <% for (int questionId : questionIds) { %>
        <%-- Fetch question details for the current questionId --%>
        <% Question currentQuestion = fetchQuestionFromDatabase(questionId); %>
        
        <%-- Generate UI for the current question based on its type --%>
        <div class="question-container">
            <h2>Question <%= currentQuestion.getQuestionId() %>:</h2>
            <div class="question-text"><%= currentQuestion.getQuestionText() %></div>
            
            <%-- Add UI generation logic based on question type using TakeQuiz helper class --%>
            <% TakeQuiz quizHelper = new TakeQuiz(); %>
            <%= quizHelper.generateUI(currentQuestion.getQuestionType().ordinal() + 1, currentQuestion) %>
        </div>
        
        <%-- Add navigation or submission logic for handling user input and moving to the next question --%>
        <form action="QuestionSevlet" method="post">
            <input type="hidden" name="quizId" value="<%= quiz.getQuizID() %>">
            <input type="hidden" name="questionId" value="<%= currentQuestion.getQuestionId() %>">
            <button type="submit">Next Question</button>
        </form>
        
        <% break; %> <%-- Break after displaying one question if only one is to be displayed at a time --%>
    <% } %>

    <!-- Additional HTML content or scripts as needed -->
</body>
</html>
