<%@ page import="java.util.List" %>
<%@ page import="am.itspace.authorbookee.model.Author" %>
<%--
  Created by IntelliJ IDEA.
  User: hovha
  Date: 11.11.2024
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
</head>
<body>
<h1>Authors:</h1> <a href="/addAuthor">Add Author</a>
<% List<Author> authors = (List<Author>) request.getAttribute("authors"); %>
<table>
<tr>
    <th>id</th>
    <th>name</th>
    <th>surname</th>
    <th>phone</th>
    <th>gender</th>
</tr>

<% for (Author author : authors)  { %>
<tr>
    <td><%= author.getId() %></td>
    <td><%= author.getName() %></td>
    <td><%= author.getSurname() %></td>
    <td><%= author.getPhone() %></td>
    <td><%= author.getGender().name() %></td>
</tr>
<% } %>
</table>
</body>
</html>
