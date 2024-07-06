<%@ page import="Models.Account"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
    <style>
        body {
            font-family: 'Raleway', sans-serif;
            background-color: #1B1616;
            margin: 0;
            padding: 20px;
        }
        h1 {
            color: #fc6916;
        }
        p {
            color: #fe9ce3;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            background-color: #444444;
            margin: 10px 0;
            padding: 10px;
            border: 1px solid #444444;
            border-radius: 5px;
            display: flex;
            align-items: center;
        }
        img {
            border-radius: 50%;
            margin-right: 15px;
        }

        button {
            background-color: #fc6916;
            color: #1B1616;
            margin: 10px;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #fe9ce3;
            color: #1B1616;
        }
    </style>
</head>
<body>
<h1>Search Results</h1>
<%
    // Get the query parameter from the request
    String query = request.getParameter("query");

    // Get the search results from the request scope
    List<Account> results = (List<Account>) request.getAttribute("results");

    // Check if query is not null and not empty
    if (query != null && !query.trim().isEmpty()) {
        out.println("<p>You searched for: " + query + "</p>");
        if (results != null && !results.isEmpty()) {
            out.println("<ul>");
            for (Account account : results) {
                out.println("<li>");
                out.println("<img src='" + account.getImageUrl() + "' alt='Image of " + account.getFirstName() + " " + account.getLastName() + "' style='width:50px;height:50px;'> ");
                out.println("<div>");
                out.println(account.getFirstName() + " " + account.getLastName() + " (" + account.getUserName() + ")");
                out.println("</div>");
                out.println("<a href='ProfileServlet?username=" + account.getUserName() + "'><button class='profile-button'>See Profile</button></a>");
                out.println("</li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>No results found for your query.</p>");
        }
    } else {
        out.println("<p>Please enter a search query.</p>");
    }
%>
</body>
</html>
