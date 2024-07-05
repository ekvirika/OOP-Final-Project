<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.LeaderboardEntry" %>
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
<%
    List<LeaderboardEntry> leaderboard = (List<LeaderboardEntry>) request.getAttribute("leaderboard");
%>
<div class="container">
    <h1>Quiz Statistics</h1>

    <h2>Your Results</h2>
    <ul>
        <li>Quiz : <%= request.getAttribute("quizName") %></li>
        <li>User: <%= request.getAttribute("username") %></li>
        <li>Score: <%= request.getAttribute("score") %></li>
        <li>Time Taken: <%= request.getAttribute("timeTakenSeconds") %> seconds</li>
    </ul>


    <h2>Leaderboard</h2>
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
                int rank = 1;
                for (LeaderboardEntry entry : leaderboard) {
            %>
            <tr>
                <td><%= rank %></td>
                <td><%= entry.getUsername() %></td>
                <td><%= entry.getScore() %></td>
                <td><%= entry.getElapsedTime() %> seconds</td>
            </tr>
            <%
                    rank++;
                }
            %>
            </tbody>
        </table>

    <div class="actions">
        <a href="/HomePageServlet">Go to Home</a>
    </div>
</div>
</body>
</html>
