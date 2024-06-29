<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quiz Website</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<!-- Navigation Bar -->
<nav>
    <ul>
        <li><a href="profile.jsp">Profile</a></li>
        <li><a href="logout.jsp">Logout</a></li>
    </ul>
</nav>

<!-- Welcome Message -->
<header>
    <h1>Welcome to the Quiz Website!</h1>
</header>

<!-- Leaderboard -->
<section>
    <h2>Leaderboard</h2>
    <table>
        <thead>
        <tr>
            <th>Rank</th>
            <th>User</th>
            <th>Score</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${leaderboard}">
            <tr>
                <td>${user.rank}</td>
                <td>${user.username}</td>
                <td>${user.score}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

<!-- New Quizzes -->
<section>
    <h2>New Quizzes</h2>
    <ul>
        <c:forEach var="quiz" items="${newQuizzes}">
            <li>
                <a href="quiz.jsp?id=${quiz.id}">${quiz.title}</a>
            </li>
        </c:forEach>
    </ul>
</section>

<!-- Footer -->
<footer>
    <p>&copy; 2024 Quiz Website. All rights reserved.</p>
</footer>
</body>
</html>
