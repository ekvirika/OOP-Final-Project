<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Question" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Quiz</title>
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="css/CreateQuiz.css">
</head>
<body>
<div class="container">
    <h1>Create New Quiz</h1>

    <form id="createQuizForm" action="CreateQuestionServlet" method="get">
        <div class="questions-list">
            <c:forEach var="question" items="${sessionScope.questions}">
                <div class="question">
                    <p>${question.questionText}</p>
                </div>
            </c:forEach>
        </div>
        <div class="buttons">
            <label for="questionType">Select QuestionType</label>
            <select id="questionType" name="questionType">
                <option value="QUESTION_RESPONSE">Question Response</option>
                <option value="FILL_IN_THE_BLANK">Fill in the Blank</option>
                <option value="MULTIPLE_CHOICE">Multiple Choice</option>
                <option value="PICTURE_RESPONSE">Picture Response</option>
                <option value="MULTI_ANSWER">Multi Answer</option>
                <option value="MULTIPLE_CHOICE_WITH_ANSWERS">Multiple Choice with Answers</option>
                <option value="MATCHING">Matching</option>
            </select>
            <input type="hidden" id="hiddenQuestionType" name="hiddenQuestionType">
            <button type="submit" id="addQuestionBtn">Add Question</button>
        </div>
    </form>
    <button type="button" id="saveQuiz">Save Quiz</button>
</div>

<script>
    document.getElementById('addQuestionBtn').addEventListener('click', function() {
        const questionType = document.getElementById('questionType').value;
        document.getElementById('hiddenQuestionType').value = questionType;
    });

    document.getElementById('saveQuiz').addEventListener('click', function() {
        window.location.href = 'HomePageServlet';
    });
</script>
</body>
</html>
