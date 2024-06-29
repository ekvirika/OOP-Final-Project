<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="./assets/Logo.png">
    <title>Login Form</title>
    <link rel="stylesheet" href="./css/Authorisation.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
    <link rel="icon" href="./assets/Logo.png">
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <form action="/AuthorisationServlet" method="post">
        <h2>Username or password was incorrect. Try again.</h2>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="btn">Login</button>
    </form>
    <a class="link" href="./Registration.jsp">Don't have Account? Sign up</a>
</div>
</body>
</html>
