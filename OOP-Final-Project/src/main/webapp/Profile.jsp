<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="https://cdn.dribbble.com/users/3975278/screenshots/17581195/media/2bd56e0a2606bfdf3c11edb74875658c.png?resize=800x600&vertical=center">
    <title>User Profile</title>
    <style>
        /* Load Roboto font from Google Fonts */
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap');

        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #1B1616; /* Background color */
            color: #EEEDE5; /* Text color */
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #EEEDE5; /* Container background color */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            color: #1B1616; /* Adjusted text color */
            text-align: center;
        }
        .profile-picture {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 20px;
        }
        .profile-info {
            margin-bottom: 20px;
            text-align: left; /* Align text left */
            padding-left: 20px; /* Add padding for text alignment */
        }
        .profile-info h2 {
            color: #FC6916; /* Header color */
            margin-bottom: 10px;
        }
        .profile-info p {
            margin: 5px 0;
        }
        .achievements {
            margin-bottom: 20px;
        }
        .achievements h3 {
            color: #FC6916; /* Header color */
            margin-bottom: 10px;
        }
        .quizzes {
            margin-bottom: 20px;
        }
        .quizzes h3 {
            color: #FC6916; /* Header color */
            margin-bottom: 10px;
        }
        .friends {
            margin-bottom: 20px;
        }
        .friends h3 {
            color: #FC6916; /* Header color */
            margin-bottom: 10px;
        }
        .friends ul {
            list-style-type: none;
            padding: 0;
        }
        .friends ul li {
            margin-bottom: 5px;
        }
    </style>
</head>
<body>

<div class="container">
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTugu0kegXOT1Gh1sgDVHvYjkGW29w19Hl9gQ&s" alt="Profile Picture" class="profile-picture">

    <div class="profile-info">
        <h2>User Profile</h2>
        <p><strong>Name:</strong> John Doe</p> <!-- Replace with actual name -->
        <p><strong>Username:</strong> johndoe123</p> <!-- Replace with actual username -->
    </div>

    <div class="achievements">
        <h3>Achievements</h3>
        <p>No achievements yet.</p> <!-- Replace with actual achievements if available -->
    </div>

    <div class="quizzes">
        <h3>Quizzes Created</h3>
        <p>No quizzes created yet.</p> <!-- Replace with actual list of quizzes created -->
    </div>

    <div class="friends">
        <h3>Friends</h3>
        <ul>
            <li>Jane Smith</li> <!-- Replace with actual list of friends -->
            <li>Mike Johnson</li>
            <li>Sara Williams</li>
        </ul>
    </div>
</div>

</body>
</html>
