<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quiz Statistics</title>
    <link rel="stylesheet" href="./css/QuizStats.css">
</head>
<body>
<div class="container">
    <h1>Quiz Statistics</h1>
    <div class="user-stats">
        <h2>Your Results</h2>
        <p>Quiz ID: <%= request.getAttribute("quizId") %></p>
        <p>Username: <%= request.getSession().getAttribute("username") %></p>
        <p>Score: <%= request.getAttribute("quizScore") %></p>
        <p>Time Taken: <%= request.getAttribute("elapsedTime") %> seconds</p>
    </div>

    <div class="leaderboard">
        <h2>Leaderboard</h2>
        <table>
            <thead>
            <tr>
                <th>Rank</th>
                <th>Username</th>
                <th>Score</th>
                <th>Time Taken</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Map<String, Object>> leaderboard = (List<Map<String, Object>>) request.getAttribute("leaderboard");
                for (Map<String, Object> entry : leaderboard) {
            %>
            <tr>
                <td><%= entry.get("rank") %></td>
                <td><%= entry.get("username") %></td>
                <td><%= entry.get("score") %></td>
                <td><%= entry.get("timeTaken") %> seconds</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <div class="actions">
        <a href="/HomePageServlet">Go to Home</a>
    </div>
</div>
</body>
</html>
