<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="Models.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.Quiz" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Achievement" %>
<%@ page import="java.util.Set" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/NavBar.css?v=2.0">
    <link rel="stylesheet" href="./css/ProfilePage.css">
    <title>User Profile</title>
    <link rel="icon" href="./assets/Logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">

    <style>
        .modal {
            display: none;
            position: fixed;
            z-index: 10000 !important;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: #444444;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 600px;
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        .friends h3 {
            font-size: 1.5em;
            color: #333;
            margin-bottom: 10px;
        }

        .friends ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        .friends li {
            margin: 5px 0;
        }

        .friends a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }

        .friends a:hover {
            text-decoration: underline;
            color: #0056b3;
        }

    </style>
</head>
<body>
<%
    Account account = (Account) request.getAttribute("account");
    String currentUsername = (String) request.getSession().getAttribute("username");
    boolean isSelf = (Boolean) request.getAttribute("isSelf");
    boolean isAdmin = (Boolean) request.getAttribute("isAdmin");
    List<Quiz> quizList = (List<Quiz>) request.getAttribute("quizList");
    List<String> friendsList = (List<String>) request.getAttribute("friendsList");
    boolean isFriend = friendsList.contains(currentUsername);
    Set<Achievement> achievementList = (Set<Achievement>) request.getAttribute("achievementList");
%>
<header class="animate__animated">
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

<div class="container">
    <div class="top">
        <div class="profile-picture-container animate__animated animate__jackInTheBox">
            <img src="<%= ((Account) request.getAttribute("account")).getImageUrl() %>" alt="Profile Picture"
                 class="profile-picture">
        </div>
        <div class="profile-info selected animate__animated animate__fadeIn">
            <input type="hidden" id="receiverId"
                   value="<%= ((Account) request.getAttribute("account")).getUserName() %>">
            <h2>User Profile</h2>
            <p>
                <strong>Name:</strong> <span
                    class="val"><%= ((Account) request.getAttribute("account")).getFirstName() %></span>
            </p>
            <p>
                <strong>Last name:</strong> <span
                    class="val"><%= ((Account) request.getAttribute("account")).getLastName() %></span>
            </p>
            <p>
                <strong>Username:</strong> <span
                    class="val"><%= ((Account) request.getAttribute("account")).getUserName() %></span>
            </p>
            <p>
                <strong>Email:</strong> <span
                    class="val"><%= ((Account) request.getAttribute("account")).getEmail() %></span>
            </p>
            <% if (isSelf) { %>
            <form action="ProfileServlet" method="post">
                <button type="submit" name="action" value="editProfile" class="submit">Edit Profile</button>
            </form>
            <% } else { %>
            <button type="button" class="submit" id="sendNote">Send Note</button>
            <button type="button" class="submit" id="challenge">Challenge</button>
            <% if (!isFriend) { %>
            <button type="submit" class="submit" id="addFriend">Add Friend</button>
            <% } %>

            <div id="challengeModal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <label for="quizInput">Quiz Link</label><textarea id="quizInput" rows="2" cols="50"></textarea>
                    <div></div>
                    <label for="scoreInput">High Score</label><textarea id="scoreInput" rows="2" cols="50"></textarea>
                    <div></div>
                    <button type="submit" id="sendQuizBtn">Send</button>
                </div>
            </div>

            <div id="noteModal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <label for="messageInput">Write your message: </label><textarea id="messageInput" rows="4"
                                                                                    cols="50"></textarea>
                    <div></div>
                    <button type="submit" id="sendNoteBtn">Send</button>
                </div>
            </div>
            <% } %>
            <% if (isAdmin && !isSelf) { %>
            <form action="ProfileServlet" method="post" id="deleteProfileForm">
                <input type="hidden" name="action" value="deleteProfile">
                <input type="hidden" name="username" value="<%= account.getUserName() %>">
                <!-- Make sure you have 'user' object with the 'username' field -->
                <button type="submit" class="submit" id="deleteProfileButton">Delete User</button>
            </form>

            <form action="ProfileServlet" method="post" id="makeAdmin">
                <input type="hidden" name="action" value="makeAdmin">
                <input type="hidden" name="username" value="<%= account.getUserName() %>">
                <!-- Make sure you have 'user' object with the 'username' field -->
                <button type="submit" class="submit" id="makeAdminButton">Make Admin</button>
            </form>

            <% } %>


        </div>

        <div class="achievements animate__animated animate__fadeInRight">
            <h3>Achievements</h3>
            <div class="inner">
                <%
                    for (Achievement achievement : achievementList) {
                %>
                <div class="achievement tooltip">
                    <img class="achievementImg" src="<%=achievement.getAchievementUrl()%>" alt="">
                    <p><%=achievement.getAchievementName()%>
                    </p>
                    <span class="tooltiptext"><%=achievement.getAchievementDescription()%></span>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
    <div class="bottom animate__animated animate__fadeInUp">
        <div class="quizzes">
            <h3>Quizzes Created</h3>
            <ul>
                <%
                    for (Quiz quiz : quizList) {
                %>
                <li><a href="QuizServlet?quizId=<%= quiz.getQuizID()%>"><%= quiz.getQuizName() %>
                </a></li>
                <%
                    }
                %>
            </ul>

        </div>

        <div class="friends">
            <h3>Friends</h3>
            <ul>
                <%
                    for (String friend : friendsList) {
                %>
                <li><a href="ProfileServlet?username=<%= friend %>"><%= friend %>
                </a></li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</div>
<script src="javascript/SendNotification.js" defer></script>

<script>
    document.getElementById('deleteProfileButton').onclick = function (event) {
        event.preventDefault(); // Prevent the form from submitting the traditional way

        fetch('ProfileServlet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                action: 'deleteProfile',
                username: '<%= account.getUserName() %>' // Assuming you have the username available in JSP
            })
        })
            .then(response => {
                console.log(response)
                if (response.ok) {
                    // Redirect to HomePageServlet
                    window.location.href = 'HomePageServlet';
                } else {
                    alert('Failed to delete profile. Please try again.');
                }
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('An error occurred while deleting the profile. Please try again.');
            });
    }
</script>
</body>
</html>