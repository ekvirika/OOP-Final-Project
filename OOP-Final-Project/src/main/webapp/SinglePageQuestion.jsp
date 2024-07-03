<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Quiz" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Single Page Quiz</title>
    <link rel="stylesheet" href="./css/SingleQuestionResponse.css">
    <!-- Add your CSS links and any other meta tags or scripts -->
</head>
<body>

<div class="quiz-container">
    <% Quiz currentQuiz = (Quiz) request.getAttribute("currentQuiz");
        if (currentQuiz != null) { %>
    <h1>Quiz: <%= currentQuiz.getQuizName() %></h1>
    <h2>Question <%= ((Integer) request.getAttribute("questionIndex")) + 1 %></h2>
    <div class="question-html">
        <%= (String) request.getAttribute("questionHtml") %>
    </div>

    <form action="QuestionServlet" method="post">
        <input type="hidden" name="quizId" value="<%= ((Integer) request.getAttribute("quizId")) %>">
        <input type="text" name="userAnswer" placeholder="Your Answer">
        <button type="submit">Submit Answer</button>
    </form>
    <% } else { %>
    <h1>Quiz Not Found</h1>
    <!-- Handle the case where currentQuiz is null -->
    <% } %>
</div>

</body>
</html>
