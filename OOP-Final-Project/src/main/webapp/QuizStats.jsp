<%@ page import="java.util.List" %>
<%@ page import="Models.LeaderboardEntry" %>
<%@ page import="Models.QuizHistory" %>
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

    <h2>Your Results</h2>
    <ul>
        <li>Quiz : <%= request.getAttribute("quizName") %></li>
        <li>User: <%= request.getAttribute("username") %></li>
        <li>Score: <%= request.getAttribute("score") %></li>
        <li>Time Taken: <%= request.getAttribute("timeTakenSeconds") %> seconds</li>
    </ul>

    <h2>Personal Quiz History</h2>
    <div class="quiz-history">
        <table>
            <thead>
            <tr>
                <th>Score</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Elapsed Time</th>
            </tr>
            </thead>
            <tbody>
            <% List<QuizHistory> quizHistory = (List<QuizHistory>) request.getAttribute("personalHistory");
                if (quizHistory != null) {
                    for (QuizHistory history : quizHistory) { %>
            <tr>
                <td><%= history.getQuizScore() %></td>
                <td><%= history.getStartTime() %></td>
                <td><%= history.getEndTime() %></td>
                <td><%= history.getElapsedTime() %></td>
            </tr>
            <% }
            } else { %>
            <tr><td colspan="5">No quiz history available.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <h2>Leaderboard</h2>
    <div class="leaderboard">
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
            <% List<LeaderboardEntry> leaderboard = (List<LeaderboardEntry>) request.getAttribute("leaderboard");
                if (leaderboard != null) {
                    int rank = 1;
                    for (LeaderboardEntry entry : leaderboard) { %>
            <tr>
                <td><%= rank %></td>
                <td><%= entry.getUsername() %></td>
                <td><%= entry.getScore() %></td>
                <td><%= entry.getElapsedTime() %> seconds</td>
            </tr>
            <% rank++;
            }
            } else { %>
            <tr><td colspan="4">No leaderboard data available.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <div class="actions">
        <a href="/HomePageServlet">Go Home</a>
        <a href="/QuizServlet">Retake Quiz</a>
    </div>
</div>
</body>
</html>
