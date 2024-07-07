<%@ page import="java.util.*" %>
<%@ page import="Models.Question" %>
<%@ page import="Models.Enums.QuestionType" %>
<%@ page import="utils.TakeSinglePageQuiz" %>

<jsp:useBean id="takeQuiz" class="utils.TakeSinglePageQuiz" />

<html>
<head>
    <title>Single Page Quiz</title>
    <link rel="stylesheet" href="./css/SinglePageQuestion.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
</head>
<body>
<h1><%= ((Models.Quiz) request.getAttribute("currentQuiz")).getQuizName() %></h1>
<form action="QuestionServlet" method="post" onsubmit="prepareUserAnswers(event)">
    <input type="hidden" name="quizId" value="<%= request.getAttribute("quizId") %>">

    <%
        List<Question> questions = (List<Question>) request.getAttribute("questions");
        for (Question question : questions) {
            String questionHtml = takeQuiz.generateUI(question.getQuestionType(), question);
    %>
    <div>
        <h2>Question <%= question.getQuestionId() %></h2>
        <div><%= questionHtml %></div>
        <input type="hidden" name="questionId_<%= question.getQuestionId() %>" value="<%= question.getQuestionId() %>">
    </div>
    <%
        }
    %>

    <button type="submit">Submit</button>
</form>

<script src="javascript/MatchingQuestion.js"></script>
<script>
    function prepareUserAnswers(event) {
        const form = event.target;

        <%
            for (Question question : questions) {
                String questionType = question.getQuestionType().toString();
                int questionId = question.getQuestionId();
        %>

        if ("<%= questionType %>" === 'MULTIPLE_CHOICE' || "<%= questionType %>" === 'MULTIPLE_CHOICE_WITH_ANSWERS') {
            const selectedAnswers = [];
            const checkboxes = form.querySelectorAll('input[name="userAnswer_<%= questionId %>"]:checked');
            checkboxes.forEach((checkbox) => { selectedAnswers.push(checkbox.value); });
            form.querySelector('input[name="userAnswers_<%= questionId %>"]').value = JSON.stringify(selectedAnswers);
        } else if ("<%= questionType %>" === 'MATCHING') {
            // const matchingPairs = {};
            // const questions = form.querySelectorAll('.marcxena[id="question"]');
            // questions.forEach((questionElem) => {
            //     const questionId = questionElem.id.split('_')[1];
            //     const selectedAnswer = form.querySelector('.answer.selected[id="answer_' + questionId + '"]');
            //     if (selectedAnswer) {
            //         matchingPairs[questionElem.innerText.trim()] = selectedAnswer.innerText.trim();
            //     }
            // });
            form.querySelector('input[name="userAnswers_<%= questionId %>"]').value = localStorage.getItem("json");
        } else if ("<%= questionType %>" === 'MULTI_ANSWER') {
            const answers = [];
            const inputs = form.querySelectorAll('input[name="userAnswer_<%= questionId %>"]');
            inputs.forEach((input) => { answers.push(input.value); });
            console.log(answers);
            form.querySelector('input[name="userAnswers_<%= questionId %>"]').value = JSON.stringify(answers);
        }

        <%
            }
        %>
    }
</script>
</body>
</html>
