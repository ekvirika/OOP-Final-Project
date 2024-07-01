<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="./assets/Logo.png">
    <title>Matching Quiz</title>
    <link rel="stylesheet" href="css/SingleMatching.css">
</head>
<body>
<div class="container">
    <h1>Dami-Connecte Zma</h1>
    <div class="quiz">
        <div class="questions">
            <div class="question" id="question1" onclick="selectQuestion('question1')">Messi</div>
            <div class="question" id="question2" onclick="selectQuestion('question2')">Ronaldo</div>
            <div class="question" id="question3" onclick="selectQuestion('question3')">Real</div>
            <div class="question" id="question4" onclick="selectQuestion('question4')">Barcelona</div>
        </div>
        <div class="answers">
            <div class="answer" id="answer1" onclick="selectAnswer('answer1')">Gandz</div>
            <div class="answer" id="answer2" onclick="selectAnswer('answer2')">Bandz</div>
            <div class="answer" id="answer3" onclick="selectAnswer('answer3')">Gandz</div>
            <div class="answer" id="answer4" onclick="selectAnswer('answer4')">Bandz</div>
        </div>
    </div>
    <button class="btn">Submit</button>
</div>
<script src="javascript/SingleMatch.js"></script>
</body>
</html>
