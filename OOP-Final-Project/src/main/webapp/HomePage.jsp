<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.LeaderboardEntry" %>
<%@ page import="Models.Quiz" %>
<%@ page import="Models.Notification" %>
<%@ page import="Models.QuizHistory" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="./css/HomePage.css">
    <link rel="stylesheet" href="./css/StartPage.css">
    <link rel="stylesheet" href="./css/NavBar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
</head>
<body>
<header class="animate__animated animate__fadeInDown">
    <div class="logo-area">
        <a href="/HomePageServlet">
            <img src="./assets/Logo.png" alt="Logo">
        </a>
        <span class="website-name">Q<span class="u">u</span><span class="i">i</span>zzz</span>
    </div>
    <div class="nav-bar">
        <ul>
            <li><a href="/HomePageServlet">Quizzies</a></li>
            <li><a href="/ProfileServlet">Profile</a></li>
            <li><a href="/LogoutServlet">Logout</a></li>
        </ul>
    </div>
</header>

<h2>Welcome to the Quizzz.com, <%= request.getAttribute("username") %>!</h2>
<div class="search-container">
    <form id="search-form" class="search-form" action="SuggestionServlet" method="get">
        <input id="search-input" class="search-input" type="text" name="query" placeholder="Search A Friend...">
        <button class="search-button" type="submit">
            <i class="fa fa-search"></i>
        </button>
        <div id="suggestions" class="suggestions-container"></div>
    </form>
</div>


<div class="container">
    <button class="tablink" id="firstTab" onclick="openPage('Quizzes', this, '#F5EAEC')">Quiz news</button>
    <button class="tablink" onclick="openPage('personalAcivity', this, '#FE9CE3')">My Activity</button>
    <button class="tablink" onclick="openPage('friendActivity', this, '#17A6E8')">Friend's Activity</button>
    <button class="tablink" onclick="openPage('notifications', this, '#dc5103')">Notifications</button>

    <div id="Quizzes" class="tabcontent">
        <h2>Explore Quizzes</h2>
        <form action="CreateQuizServlet" method="get">
            <button class="button-5" role="button">Create Quiz</button>
        </form>
        <div class="quizzes-inside">
            <div class="new">
                <h3>Newly added Quizzes</h3>
                <ul>
                    <%
                        List<Quiz> recentQuizHistory = (List) request.getAttribute("recentQuizzes");
                        if (recentQuizHistory != null) {
                            for (Quiz obj : recentQuizHistory) {

                    %>
                    <li><a href="QuizServlet?quizId=<%= obj.getQuizID() %>"><%= obj.getQuizName() %>
                    </a>
                    </li>
                    <%
                            }
                        }
                    %>
                </ul>
            </div>
            <div class="popular">
                <h3>Popular Quizzes</h3>
                <ul>
                    <%
                        List<Quiz> popularQuizzes = (List) request.getAttribute("popularQuizzes");
                        if (popularQuizzes != null) {
                            for (Quiz obj : popularQuizzes) {

                    %>
                    <li><a href="QuizServlet?quizId=<%= obj.getQuizID() %>"><%= obj.getQuizName() %>
                    </a>
                    </li>
                    <%
                            }
                        }
                    %>
                </ul>
            </div>
        </div>
        <%--            <%--%>
        <%--                List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes");--%>
        <%--                for (Quiz quiz : quizzes) {--%>
        <%--            %>--%>
        <%--            <li class="quiz-item">--%>
        <%--                <h2><a href="QuizServlet?quizId=<%= quiz.getQuizID() %>"><%= quiz.getQuizName() %>--%>
        <%--                </a></h2>--%>
        <%--            </li>--%>
        <%--            <% } %>--%>
        <%--        </ul>--%>
    </div>
    <div id="personalAcivity" class="tabcontent">
        <h3>My Activity</h3>
        <%
            List<Quiz> createdQuizzes = (List) request.getAttribute("recentQuizzes");
            if (createdQuizzes != null && !createdQuizzes.isEmpty()) {
        %>
        <h3>My Quiz Creating Activities</h3>
        <ul>
            <%
                for (Object obj : createdQuizzes) {
                    Quiz quiz = (Quiz) obj;
            %>
            <li><a href="QuizServlet?quizId=<%= quiz.getQuizID() %>"><%= quiz.getQuizName() %>
            </a></li>
            <%
                }
            %>
        </ul>
        <%
            }
        %>
    </div>

    <div id="friendActivity" class="tabcontent">
        <h3>Friends' Recent Activities</h3>
        <%--        <ul>--%>
        <%--            <%--%>
        <%--                java.util.List friendsActivities = (java.util.List) request.getAttribute("friendsActivities");--%>
        <%--                if (friendsActivities != null) {--%>
        <%--                    for (Object obj : friendsActivities) {--%>
        <%--                        FriendActivity activity = (FriendActivity) obj;--%>
        <%--            %>--%>
        <%--            <li><a href="UserServlet?username=<%= activity.getUsername() %>"><%= activity.getUsername() %></a> - <%= activity.getActivityDescription() %></li>--%>
        <%--            <%--%>
        <%--                    }--%>
        <%--                }--%>
        <%--            %>--%>
        <%--        </ul>--%>
    </div>

    <div id="notifications" class="tabcontent">
        <h3>Notifications</h3>
        <ul>
            <%
                List notifications = (List) request.getAttribute("notifications");
                if (notifications != null) {
                    for (Object obj : notifications) {
                        Notification notification = (Notification) obj;
            %>
            <li>You have new notification from <%= notification.getUsernameFrom() %>
                : <%= notification.getMessage() %>
            </li>
            <%
                }
            %>
            <%
            } else {
            %>
            <h4>You don't have any notifications yet.</h4>
            <%
                }
            %>
        </ul>
    </div>
</div>
<script src="javascript/SearchBar.js" defer></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        let item = document.querySelector('#firstTab');
        item.click();

        const searchContainer = document.querySelector('.search-container');
        const searchInput = document.getElementById('search-input');

        searchInput.addEventListener('focus', function () {
            searchContainer.classList.add('expanded');
        });

        searchInput.addEventListener('blur', function () {
            if (searchInput.value.trim() === '') {
                searchContainer.classList.remove('expanded');
            }
        });
    });

    function openPage(pageName, elmnt, color) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }

        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].style.backgroundColor = "";
        }

        document.getElementById(pageName).style.display = "block";

        elmnt.style.backgroundColor = color;
    }

    document.getElementById("defaultOpen").click();

</script>
</body>
</html>