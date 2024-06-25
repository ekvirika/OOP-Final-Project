<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="https://cdn.dribbble.com/users/3975278/screenshots/17581195/media/2bd56e0a2606bfdf3c11edb74875658c.png?resize=800x600&vertical=center">
    <title>Quiz Description</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #1B1616; /* Background color */
            color: #FE9CE3; /* Text color */
            margin: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }
        header {
            margin-bottom: 20px;
        }
        header h1 {
            font-size: 36px;
            color: #FC6916;
        }
        .description {
            max-width: 600px;
            margin-bottom: 40px;
        }
        .start-btn {
            padding: 15px 30px;
            font-size: 18px;
            background-color: #FC6916; /* Button color */
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .start-btn:hover {
            background-color: #EDC433; /* Button hover color */
        }
    </style>
</head>
<body>

<header>
    <h1>Quiz Description</h1>
</header>

<div class="description">
    <p>Get ready to challenge your knowledge with our exciting quiz! This quiz will test your understanding on a variety of topics. Make sure you're prepared, as some questions might be tricky!</p>
    <p>Take your time to read each question carefully and select the best possible answer. Good luck and have fun!</p>
</div>

<button class="start-btn" onclick="location.href='quiz.html'">Start Quiz</button>

</body>
</html>
