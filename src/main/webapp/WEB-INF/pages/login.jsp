<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF=8"/>
    <title>Shop</title>
    <link href="/static/css/style.css" rel="stylesheet"  type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div id="wrapper">

            <div id="header">

                <img src="/static/images/d4.png" width="150" height="150" id="rez" alt="clock" align="left">
                <p >Wheels and Discs</p>

            </div>
                <div id="content">


                    <nav class="navbar navbar-default">

                        <div class="container-fluid">
                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                <ul id="groupList" class="nav navbar-nav"  >



                                    <c:url value="/j_spring_security_check" var="loginUrl" />

                                    <li><form action="${loginUrl}" method="POST">
                                            Login:   <input class="btn btn-default navbar-btn" type="text" name="j_login" size="10">
                                         Password: <input class="btn btn-default navbar-btn" type="password" name="j_password" size="10">
                                        <input class="btn btn-default navbar-btn" type="submit" value="Enter" />

                                    </form></li>
                                    <li><input  class="btn btn-default navbar-btn" type="submit"   value="Register" onClick='location.href="/register"'></li>

                                       <%--<button type="button" id="register" class="btn btn-default navbar-btn">Register</button> </li>--%>
                                </ul>




                            </div><!-- /.navbar-collapse -->
                        </div><!-- /.container-fluid -->
                    </nav>
                    <div align="center">
                        <c:url value="/j_spring_security_check" var="loginUrl" />


                        <form action="${loginUrl}" method="POST">


                            <c:if test="${param.error ne null}">
                               <p> Wrong login or password!</p>
                            </c:if>

                            <c:if test="${param.logout ne null}">

                            </c:if>
                        </form>
                    </div>



                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <td></td>
                            <td><b>Type</b></td>
                            <td><b>Brand</b></td>
                            <td><b>Diametr</b></td>
                            <td><b>Price($ USA)</b></td>

                        </tr>
                        </thead>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <td></td>
                                <c:choose>
                                    <c:when test="${product.type ne null}">
                                        <td>${product.type.name}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Default</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${product.brand}</td>
                                <td>${product.diametr}</td>
                                <td>${product.price}</td>

                            </tr>
                        </c:forEach>
                    </table>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <c:if test="${allPages ne null}">
                                <c:forEach var="i" begin="1" end="${allPages}">
                                    <li><a href="/login/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                                </c:forEach>
                            </c:if>
                            <%--<c:if test="${byTypePages ne null}">--%>
                                <%--<c:forEach var="i" begin="1" end="${byTypePages}">--%>
                                    <%--<li><a href="/type/${typeId}?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                        </ul>
                    </nav>


                </div>
    </div>



<script>
    $('.dropdown-toggle').dropdown();

    $('#register').click(function(){
        window.location.href="/register";
    });

    $('#add_product').click(function(){
        window.location.href='/product_add_page';
    });
    $('#basket').click(function(){
        window.location.href='/basket_page';
    });
    $('#basket2').click(function(){
        window.location.href='/basket_page2';
    });
    $('#admin').click(function(){
        window.location.href='/admin';
    });


    $('#delete_product').click(function(){
        var data = { 'toDelete[]' : []};
        $(":checked").each(function() {
            data['toDelete[]'].push($(this).val());
        });
        $.post("/product/delete", data, function(data, status) {
            window.location.reload();
        });
    });

    $('#order_product').click(function(){
        var data = { 'toDelete[]' : []};
        $(":checked").each(function() {
            data['toDelete[]'].push($(this).val());
        });
        $.post("/product/order", data, function(data, status) {
            window.location.reload();
        });
    });

</script>
</body>
</html>
