<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>New Product</title>
        <link href="/static/css/style.css" rel="stylesheet"  type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
    <div id="wrapper">

        <div id="header">

            <img src="/static/images/d4.png" width="150" height="150" id="rez" align="left">
            <p >Wheels and Discs</p>
        </div>
        <div class="container">

            <form role="form" class="form-horizontal" action="/product/add" method="post">
                        <h3>New product</h3>
                <select class="selectpicker form-control form-group" name="type">
                    <option value="-1">Default</option>
                    <c:forEach items="${types}" var="type">
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
                        <input class="form-control form-group" type="text" name="brand" placeholder="Brand">
                        <input class="form-control form-group" type="text" name="diametr" placeholder="Diametr">
                        <input class="form-control form-group" type="text" name="price" placeholder="Price">
                    <input type="submit" class="btn btn-primary" value="Add">
                <input type="button"  class="btn btn-default navbar-btn" value="Go to Main Menu" onClick='location.href="/"'>
            </form>
        </div>

        <script>
            $('.selectpicker').selectpicker();
        </script>

    </div>

    <%--<div id="login">--%>
        <%--<div align="center">--%>
            <%--<h3>Your login is: ${login}</h3>--%>

            <%--<c:url value="/update" var="updateUrl" />--%>
            <%--<form action="${updateUrl}" method="POST" >--%>
                <%--E-mail:<br/><input type="text" name="email" value="${email}" /><br/>--%>
                <%--Phone:<br/><input type="text" name="phone" value="${phone}" /><br/>--%>
                <%--<input type="submit" value="Update" />--%>

            <%--</form>--%>

            <%--<c:url value="/logout" var="logoutUrl" />--%>
            <%--<form action="${logoutUrl}" method="POST" >--%>
                <%--<input type="submit" value="LogOut" />--%>
            <%--</form>--%>
        <%--</div>--%>


    <%--</div>--%>

    </body>
</html>