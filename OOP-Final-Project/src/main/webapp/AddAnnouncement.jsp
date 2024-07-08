<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Question" %>
<%@ page import="utils.TakeSinglePageQuiz" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Quiz" %>

<jsp:useBean id="takeQuiz" class="utils.TakeSinglePageQuiz"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Announcement</title>
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="css/AddAnnouncement.css">
</head>
<body>
    <div class="container">
        <h1>Add Announcement</h1>
        <form action="AddAnnouncementServlet" method="post">
            <label for="announcement"><h3>Announcement:</h3></label>
            <textarea id="announcement" name="announcement" required></textarea>
            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
