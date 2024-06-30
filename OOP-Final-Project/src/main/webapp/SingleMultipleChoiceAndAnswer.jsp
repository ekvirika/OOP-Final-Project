<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="css/SingleMultipleChoiceAndAnswer.css">
    <title>Multiple Choice And Answer</title>
</head>
<body>
<div class="container">
    <div class="question"><h3>Please mark each statement below which is true:</h3></div>
    <ul class="answers">
        <li>
            <input type="checkbox" id="answerA" name="answers" value="A">
            <label for="answerA">Stanford was established in 1891.</label>
        </li>
        <li>
            <input type="checkbox" id="answerB" name="answers" value="B">
            <label for="answerB">Stanford has the best computer science department in the world.</label>
        </li>
        <li>
            <input type="checkbox" id="answerC" name="answers" value="C">
            <label for="answerC">Stanford will be going to a bowl game this year.</label>
        </li>
    </ul>
    <button class="btn">Submit</button>
</div>
</body>
</html>
