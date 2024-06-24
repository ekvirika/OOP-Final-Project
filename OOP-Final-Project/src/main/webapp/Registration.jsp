<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 6/20/2024
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" href="https://cdn1.iconfinder.com/data/icons/google_jfk_icons_by_carlosjj/512/chrome.png">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome - Google Chrome</title>
    <style>
        body{
            font-family:'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
            background-color:whitesmoke;
            color:darkblue;
            text-align: left;
        }

        h1{
            display: flex;
            justify-content: center;
            margin-top: 50px;
        }

        a{
            color:blue;
            text-decoration: none;
        }

        a:hover{
            color: cadetblue;
            text-decoration: underline;
        }

        input, button{
            border: 1px solid black;
        }

        button{
            border-radius: 5px;
            background-color:whitesmoke;
            color:blue;
            cursor: pointer;
        }

        button:hover {
            background-color:yellow;
        }

        button:active {
            background-color: yellowgreen;
        }

        label, p{
            font-size: large;
        }

        p, form, a{
            margin-left: 7%;
            margin-top: 20px;
        }

    </style>
</head>
<body>
<h1>Welcome to Homework 5</h1> <br/>
<p>Please log in!</p>
<form action="Login" method="POST">
    <label for="user">User Name:</label>
    <input type="text" id="user" name="user" required>
    <br/> <br/>
    <label for="pass">Password:</label>
    <input type="password" id="pass" name="pass" required>
    <button type="submit">Login</button>
    <br/> <br/>
</form>
<a href="createNew.jsp">Create New Account</a>
</body>
</html>
