<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="https://cdn.dribbble.com/users/3975278/screenshots/17581195/media/2bd56e0a2606bfdf3c11edb74875658c.png?resize=800x600&vertical=center">
    <title>Login Form</title>
    <style>
        /* Load Roboto font from Google Fonts */
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap');

        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #1B1616;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #EEEDE5;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        .container h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #1B1616;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #1B1616;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-family: 'Roboto', Arial, sans-serif; /* Apply Roboto font */
        }
        .form-group input:focus {
            border-color: #FE9CE3;
            outline: none;
        }
        .btn {
            width: 100%;
            padding: 10px;
            background-color: #FC6916;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            font-family: 'Roboto', Arial, sans-serif; /* Apply Roboto font */
        }
        .btn:hover {
            background-color: #eda633;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Login</h2>
    <form action="/submit_login" method="post">
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
</div>

</body>
</html>
