<%--
  Created by IntelliJ IDEA.
  User: Ele
  Date: 6/25/2024
  Time: 13:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz</title>
    <link rel="stylesheet" href="./css/StartPage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">
</head>
<body>
    <header class="animate__animated animate__fadeInDown">
        <div class="logo-area">
            <img src="./assets/Logo.png" alt="Logo">
            <span class="website-name">Q<span class="u">u</span><span class="i">i</span>zzz</span>
        </div>
        <div class="nav-bar">
            <ul>
                <li><a href="./HomePage.jsp">Quizzies</a> </li>
                <li><a href="./Authorisation.jsp">Sign in</a></li>
                <li><a href="./Registration.jsp">Sign up</a></li>
            </ul>
        </div>
    </header>

    <main>  
        <div class="text animate__animated animate__fadeInLeft">
           <h1> 
               <span class="classic">Welcome to Quizzz.com, where you can </span> <br>
               <span class="first">CHALLENGE</span> <br>
               <span class="second">YOUR</span> <br>
               <span class="third">MIND</span>
            </h1>
        </div>
        <div class="animation ">
            <div class="scene animate__animated animate__fadeInRight" id="scene">
                <div class="pivot centered " id="pivot" style="transform: rotateX(-35deg) rotateY(-135deg)">
                <div class="cube" id="cube"></div>
            </div>
            <div id="guide">
                <div class="anchor" id="anchor3" style="transform: translateZ(3px) translateY(-33.33%) rotate(-270deg) translateY(66.67%)"></div>
                <div class="anchor" id="anchor2" style="transform: translateZ(3px) translateY(-33.33%) rotate(-180deg) translateY(66.67%)"></div>
                <div class="anchor" id="anchor1" style="transform: translateZ(3px) translateY(-33.33%) rotate(-90deg) translateY(66.67%)"></div>
                <div class="anchor" id="anchor0" style="transform: translateZ(3px) translateY(-33.33%) rotate(0deg) translateY(66.67%)"></div>
            </div>
        </div>
    </main>
    <script src="./javascript/StartPage.js"></script>
</body>
</html>
