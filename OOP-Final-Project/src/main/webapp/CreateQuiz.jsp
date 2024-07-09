<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Question" %>
<%@ page import="utils.TakeSinglePageQuiz" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Quiz" %>

<jsp:useBean id="takeQuiz" class="utils.TakeSinglePageQuiz"/>
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

    <% Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");%>
    <form id="quizActionsForm" action="CreateQuizServlet" method="post" onsubmit="return validateForm()">
        <input type="hidden" name="quizAction" id="quizAction">
        <div class="textboxes">
            <div class="row">
                <div class="quiz-name">
                    <label for="quizName">Quiz Name:</label>
                    <input type="text" id="quizName" name="quizName" required>
                </div>
                <div class="booleans">
                    <label>
                        <input type="checkbox" name="isSinglePage" <% if (quiz != null && quiz.isSinglePage()) { %>checked<% } %> /> Single Page Quiz
                    </label>
                    <label>
                        <input type="checkbox" name="randomizeQuestions" <% if (quiz != null && quiz.isRandomizeQuestions()) { %>checked<% } %> /> Randomize Questions
                    </label>
                    <label>
                        <input type="checkbox" name="immediateFeedback" <% if (quiz != null && quiz.isImmediateFeedback()) { %>checked<% } %> /> Immediate Feedback
                    </label>
                </div>
            </div>
            <div class="quiz-desc">
                <label for="quizDescription">Quiz Description:</label>
                <textarea id="quizDescription" name="quizDescription" required></textarea>
            </div>
        </div>

        <div class="buttons">
            <button type="button" id="saveQuiz" onclick="submitForm('save')">Save Quiz</button>
            <button type="button" id="deleteQuiz" onclick="submitForm('delete')">Delete Quiz</button>
        </div>
    </form>

    <form id="createQuizForm" action="CreateQuestionServlet" method="get">
        <div class="questions-list">
            <%
                List<Question> questions = (List<Question>) session.getAttribute("questions");
                if (questions != null) {
                    for (int i = 0; i < questions.size(); i++) {
                        Question question = questions.get(i);
                        String questionHtml = "<h2>Question " + (i + 1) + "</h2>";
                        questionHtml += takeQuiz.generateUI(question.getQuestionType(), question, true);
            %>
            <div>
                <%= questionHtml %>
                <input type="hidden" name="questionId_<%= question.getQuestionId() %>" value="<%= question.getQuestionId() %>">
            </div>
            <%
                    }
                }
            %>
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
</div>

<script>
    document.getElementById('addQuestionBtn').addEventListener('click', function () {
        const questionType = document.getElementById('questionType').value;
        document.getElementById('hiddenQuestionType').value = questionType;
    });

    function submitForm(action) {
        if (action === 'save' && !validateForm()) {
            return;
        }
        document.getElementById('quizAction').value = action;
        document.getElementById('quizActionsForm').submit();
    }

    function validateForm() {
        const quizName = document.getElementById('quizName').value;
        if (!quizName) {
            alert('Quiz Name is required.');
            return false;
        }
        return true;
    }
</script>
</body>
</html>
