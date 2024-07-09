<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Enums.NotificationType" %>
<%@ page import="Models.*" %>

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

    <style>
        /* Notification list items */
        #notifications ul {
            list-style-type: none; /* Remove default bullet points */
            padding: 0; /* Remove default padding */
            margin: 0; /* Remove default margin */
        }

        /* Individual notification item */
        #notifications li {
            background-color: #ffffff; /* White background for notifications */
            margin-bottom: 10px; /* Space between notifications */
            padding: 15px; /* Padding inside each notification */
            border-radius: 5px; /* Rounded corners */
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1); /* Subtle shadow */
            line-height: 1.6; /* Line height for readability */
            color: #1B1616;
        }

        /* Username link */
        #notifications a {
            color: #007bff; /* Link color */
            text-decoration: none; /* Remove underline from links */
        }

        /* Username link on hover */
        #notifications a:hover {
            text-decoration: underline; /* Underline on hover */
        }

        /* Buttons for accepting or rejecting friend requests */
        #notifications button {
            background-color: #007bff; /* Blue background */
            color: #ffffff; /* White text */
            border: none; /* Remove default border */
            padding: 10px 15px; /* Padding inside the button */
            border-radius: 5px; /* Rounded corners */
            cursor: pointer; /* Pointer cursor on hover */
            margin-right: 5px; /* Space between buttons */
            margin-left: 5px;
            font-size: 14px; /* Font size */
        }

        /* Button hover effects */
        #notifications button:hover {
            background-color: #0056b3; /* Darker blue on hover */
        }

        /* Button for rejecting friend requests */
        #notifications button.reject {
            background-color: #dc3545; /* Red background */
        }

        #notifications button.reject:hover {
            background-color: #c82333; /* Darker red on hover */
        }

        /* Hide the receiver ID input field */
        #receiverId {
            display: none;
        }
    </style>
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
    <button class="tablink" onclick="openPage('announcements', this, '#8264ee')">Announcements</button>
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
    </div>
    <div id="announcements" class="tabcontent">
        <h3>Announcements</h3>
        <% Account account = (Account) request.getSession().getAttribute("account");
            if(account.isAdmin()){

        %>
        <form action="AddAnnouncementServlet" method="get">
            <button class="add-announcement">Add Announcement</button>
        </form>
        <% } %>
        <div class="announcement-list">
            <%
                List<Announcement> announcements = (List<Announcement>) request.getAttribute("announcements");
                if (announcements != null) {
                    for (Announcement announcement : announcements) {
            %>
            <div class="announcement-box">
                <p class="messageText" ><strong>Message:</strong> <%= announcement.getMessage() %></p>
                <p class="time"><strong>Time:</strong> <%= announcement.getAnnouncementTime() %></p>
                <p class="author"><strong>Author:</strong> <%= announcement.getUsername() %></p>
            </div>
            <%
                    }
                }
            %>
        </div>
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
        <input type="hidden" id="receiverId"
               value="<%= ((Account) request.getAttribute("account")).getUserName() %>">
        <h3>Notifications</h3>
        <ul>
            <%
                List<Notification> notifications = (List<Notification>) request.getAttribute("notifications");
                if (notifications != null) {
                    for (Object obj : notifications) {
                        Notification notification = (Notification) obj;
                        NotificationType type = notification.getNotificationType();
            %>
            <li>
                <%
                    if (type.equals(NotificationType.FRIEND_REQUEST)) {
                %>
                <a href="ProfileServlet?username=<%= notification.getUsernameFrom() %>"><%= notification.getUsernameFrom() %></a> sent you a friend request.
                <input type="hidden" class="usernameFrom" name="usernameFrom" value="<%= notification.getUsernameFrom() %>">
                <button onclick="handleFriendRequest('yes', '<%= notification.getNotificationId() %>')">Yes</button>
                <button onclick="handleFriendRequest('no', '<%= notification.getNotificationId() %>')">No</button>
                <%
                } else if (type.equals(NotificationType.NOTE)) {
                %>
                <a href="ProfileServlet?username=<%= notification.getUsernameFrom() %>"><%= notification.getUsernameFrom() %></a> sent you a message:
                <%= notification.getMessage() %>
                <%
                } else if (type.equals(NotificationType.CHALLENGE)) {
                %>
                <a href="ProfileServlet?username=<%= notification.getUsernameFrom() %>"><%= notification.getUsernameFrom() %></a> sent you a challenge:
                <a href="<%= notification.getQuizLink() %>">Challenge Link</a>
                <%
                    }
                %>
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
<script src="javascript/SendNotification.js" defer></script>

<script>
    function handleFriendRequest(response, notificationId) {
        // let receiverId = document.getElementById("receiverId").value;
        let receiverId = document.querySelector(".usernameFrom").value;
        // Add your logic to handle friend request response (yes/no)
        if(response === "yes"){
            fetch('notificationServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    type: "pending",
                    answer: true,
                    receiver: receiverId,
                    notificationId: notificationId
                })
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Request Sent:', data);
                })
                .catch((error) => {
                    console.error('Error:', error);
                    alert('An error occurred while sending the friend request. Please try again.');
                });

        }else {
            fetch('notificationServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    type: "pending",
                    answer: false,
                    receiver: receiverId,
                    notificationId: notificationId
                })
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Request Sent:', data);
                })
                .catch((error) => {
                    console.error('Error:', error);
                    alert('An error occurred while sending the friend request. Please try again.');
                });
        }
            console.log(response, notificationId);
    }
</script>

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