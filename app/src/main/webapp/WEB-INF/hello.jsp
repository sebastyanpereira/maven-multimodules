<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%@ include file="bootstrap-css.jsp" %>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <h2>Users</h2>

            <table class="table">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Имя</th>
                    <th scope="col">Фамилия</th>
                </tr>
                </thead>
                <% if (request.getAttribute("users") != null) { %>
                <% for (User user : (List<User>) request.getAttribute("users")) { %>
                <tbody>
                <tr>
                    <td><%= user.getId() %>
                    </td>
                    <td><%= user.getName() %>
                    </td>
                    <td><%= user.getSurname() %>
                    </td>
                </tr>
                </tbody>
                <% } %>
                <% } %>
            </table>

            <h5>User Registry</h5>
            <form class="mt-3" action="<%= request.getContextPath() %>" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" id="name" name="name" placeholder="First name" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="surname" name="surname" placeholder="Last name" required>
                </div>

                <button type="submit" class="btn btn-primary mt-3">Create</button>
            </form>
        </div>
    </div>
</div>

<%@include file="bootstrap-scripts.jsp" %>
</body>
</html>
