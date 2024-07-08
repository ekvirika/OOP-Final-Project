<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Models.Account" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/ProfilePage.css">
    <link rel="stylesheet" href="./css/NavBar.css">
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
            z-index: 10000;
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

    </style>
</head>
<body>
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
            <% Boolean isSelf = (Boolean) request.getAttribute("isSelf"); %>
            <% Boolean isAdmin = (Boolean) request.getAttribute("isAdmin"); %>
            <% if (isSelf != null && isSelf) { %>
            <form action="ProfileServlet" method="post">
                <button type="submit" name="action" value="editProfile" class="submit">Edit Profile</button>
            </form>
            <% } else if (isAdmin != null && isAdmin) { %>
            <form action="ProfileServlet" method="post">
                <input type="hidden" name="username"
                       value="\<%= ((Account) request.getAttribute("account")).getUserName() %>\">
                <button type="submit" name="action" value="deleteProfile" class="submit">Delete Profile</button>
            </form>
            <% } else { %>
            <button type="button" class="submit" id="sendNote">Send Note</button>
            <button type="button" class="submit" id="challenge">Challenge</button>
            <button type="submit" class="submit" id="addFriend">Add Friend</button>

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
        </div>

        <div class="achievements animate__animated animate__fadeInRight">
            <h3>Achievements</h3>
            <p>No achievements yet.</p>
        </div>
    </div>
    <div class="bottom animate__animated animate__fadeInUp">
        <div class="quizzes">
            <h3>Quizzes Created</h3>
            <p>No quizzes created yet.</p>
            <%--             TODO--%>
        </div>

        <div class="friends">
            <h3>Friends</h3>
            <%--            TODO --%>
            <%-- Display list of friends if needed --%>
        </div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        // Get the modal
        let challengeModal = document.getElementById("challengeModal");
        let noteModal = document.getElementById("noteModal");

        // Get the buttons that open the modal
        let btnSendNote = document.getElementById("sendNote");
        let btnChallenge = document.getElementById("challenge");

        // Get the <span> elements that close the modal
        let closeButtons = document.getElementsByClassName("close");

        let receiverId = document.getElementById("receiverId").value;

        // When the user clicks on the button, open the modal
        btnSendNote.onclick = function () {
            noteModal.style.display = "block";
            challengeModal.style.display = "none";
        }

        btnChallenge.onclick = function () {
            challengeModal.style.display = "block";
            noteModal.style.display = "none";
        }

        // When the user clicks on <span> (x), close the modal
        Array.from(closeButtons).forEach(button => {
            button.onclick = function () {
                challengeModal.style.display = "none";
                noteModal.style.display = "none";
            }
        });

        // When the user clicks anywhere outside the modal, close it
        window.onclick = function (event) {
            if (event.target === challengeModal) {
                challengeModal.style.display = "none";
            }
            if (event.target === noteModal) {
                noteModal.style.display = "none";
            }
        }

        // Handle challenge
        let sendQuizBtn = document.getElementById("sendQuizBtn");
        let quizLink = document.getElementById("quizInput");
        let bestScore = document.getElementById("scoreInput");

        sendQuizBtn.onclick = function () {
            let quiz = quizLink.value.trim();
            let score = bestScore.value.trim();
            if (quiz !== "" && score !== "") {
                console.log("quiz link:", quiz);
                console.log("high score:", score);

                fetch('notificationServlet', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        type: "quiz",
                        quizLink: quiz,
                        bestScore: score,
                        receiver: receiverId
                    })
                })
                    .then(response => response.json())
                    .then(data => {
                        console.log('Success:', data);
                        challengeModal.style.display = "none";
                        quizLink.value = "";
                        bestScore.value = "";
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                        alert('An error occurred while sending the challenge. Please try again.');
                    });
            } else {
                alert('Please fill out both the quiz link and the high score.');
            }
        }

        // Handle send message
        let sendNoteBtn = document.getElementById("sendNoteBtn");
        let messageInp = document.getElementById("messageInput");

        sendNoteBtn.onclick = function () {
            let message = messageInp.value.trim();
            if (message !== "") {
                console.log("Message sent:", message);

                fetch('notificationServlet', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        type: "note",
                        message: message,
                        receiver: receiverId
                    })
                })
                    .then(response => response.json())
                    .then(data => {
                        console.log('Success:', data);
                        noteModal.style.display = "none";
                        messageInp.value = "";
                    })
                    .catch((error) => {
                        console.error('Error:', error);
                        alert('An error occurred while sending the note. Please try again.');
                    });
            } else {
                alert('Please write a message before sending.');
            }
        }

        // Handle friend request
        let friend = document.getElementById("addFriend");

        friend.onclick = function () {
            fetch('notificationServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    type: "addFriend",
                    request: 1,
                    receiver: receiverId
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
    });
</script>
</body>
</html>