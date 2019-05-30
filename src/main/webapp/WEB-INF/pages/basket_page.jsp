<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Shop</title>
    <link href="/static/css/style.css" rel="stylesheet"  type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<div id="wrapper">

    <div id="header">

        <img src="/static/images/d4.png" width="150" height="150" id="rez" align="left">
        <p >Basket of Orders</p>
    </div>
    <div id="content">



        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul id="groupList" class="nav navbar-nav">
                        <li><button type="button" id="menu" class="btn btn-default navbar-btn">Main menu</button></li>
                        <%--<li><button type="button" id="add_product" class="btn btn-default navbar-btn">Add product</button></li>--%>

                        <li><button type="button" id="delete_product" class="btn btn-default navbar-btn">Delete product</button></li>


                    </ul>

                    <%--<button type="button" id="basket" class="btn btn-default navbar-btn">Basket</button>--%>


                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

        <table class="table table-striped">
            <thead>
            <tr>
                <td></td>
                <td><b>User</b></td>
                <td><b>Date</b></td>
                <td><b>Type</b></td>
                <td><b>Brand</b></td>
                <td><b>Diametr</b></td>
                <td><b>Price</b></td>

            </tr>
            </thead>
            <c:forEach items="${basketOrder}" var="basketOrder">
                <tr>
                    <td><input type="checkbox" name="toDelete[]" value="${basketOrder.id}" id="checkbox_${basketOrder.id}"/></td>
                    <td>${basketOrder.user.login}</td>
                    <td>${basketOrder.order.date}</td>
                    <c:choose>
                        <c:when test="${basketOrder.type ne null}">
                            <td>${basketOrder.type.name}</td>
                        </c:when>
                        <c:otherwise>
                            <td>Default</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${basketOrder.brand}</td>
                    <td>${basketOrder.diametr}</td>
                    <td>${basketOrder.price}</td>

                </tr>
            </c:forEach>
        </table>

        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${allPages ne null}">
                    <c:forEach var="i" begin="1" end="${allPages}">
                        <li><a href="/basket_page/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                    </c:forEach>
                </c:if>

            </ul>
        </nav>


    </div>

    </div>
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


<script>
    $('.dropdown-toggle').dropdown();

    $('#add_product').click(function(){
        window.location.href='/product_add_page';
    });
    $('#basket').click(function(){
        window.location.href='/basket_page';
    });
    $('#menu').click(function(){
        window.location.href='/';
    });



    $('#delete_product').click(function(){
        var data = { 'toDelete[]' : []};
        $(":checked").each(function() {
            data['toDelete[]'].push($(this).val());
        });

        $.post("/order/delete", data, function(data, status) {
            window.location.reload();
        });
    });
</script>




</body>
</html>
