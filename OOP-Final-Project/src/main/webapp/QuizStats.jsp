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

    <h2>Your Results</h2>
    <ul>
        <li>Quiz : <%= request.getAttribute("quizName") %></li>
        <li>User: <%= request.getAttribute("username") %></li>
        <li>Score: <%= request.getAttribute("score") %></li>
        <li>Time Taken: <%= request.getAttribute("timeTakenSeconds") %> seconds</li>
    </ul>

<%--    <h2>Leaderboard</h2>--%>
<%--    <table>--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>Rank</th>--%>
<%--            <th>Username</th>--%>
<%--            <th>Score</th>--%>
<%--            <th>Time Taken</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <% List<Map<String, Object>> leaderboard = (List<Map<String, Object>>) request.getAttribute("leaderboard");--%>
<%--            if (leaderboard != null) {--%>
<%--                for (Map<String, Object> entry : leaderboard) {--%>
<%--        %>--%>
<%--        <tr>--%>
<%--&lt;%&ndash;            <td><%= entry.get("rank") %></td>&ndash;%&gt;--%>
<%--            <td><%= entry.get("username") %></td>--%>
<%--            <td><%= entry.get("score") %></td>--%>
<%--            <td><%= entry.get("timeTaken") %> seconds</td>--%>
<%--        </tr>--%>
<%--        <%     }--%>
<%--        }--%>
<%--        // Handle case where leaderboard is null or empty--%>
<%--        else {--%>
<%--            // Display a message or handle it gracefully--%>
<%--        }--%>
<%--        %>--%>
<%--        </tbody>--%>
<%--    </table>--%>

    <div class="actions">
        <a href="/HomePageServlet">Go to Home</a>
    </div>
</div>
</body>
</html>
