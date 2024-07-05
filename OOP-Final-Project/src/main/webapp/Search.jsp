### Search.jsp

```jsp
<%@ page import="Models.Account"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
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
                out.println("<li>" + account.getFirstName() + " " + account.getLastName() + " (" + account.getUserName() + ")</li>");
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