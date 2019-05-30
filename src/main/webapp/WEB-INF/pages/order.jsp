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
                    <p >Order</p>
                </div>
        <div id="content">



                    <nav class="navbar navbar-default">
                        <div class="container-fluid">
                            <!-- Collect the nav links, forms, and other content for toggling -->
                            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                                <ul id="groupList" class="nav navbar-nav">
                                    <li><button type="button" id="menu" class="btn btn-default navbar-btn">Main menu</button></li>
                                    <%--<li><button type="button" id="add_product" class="btn btn-default navbar-btn">Add product</button></li>--%>




                                </ul>

                                <%--<button type="button" id="basket" class="btn btn-default navbar-btn">Basket</button>--%>


                            </div><!-- /.navbar-collapse -->
                        </div><!-- /.container-fluid -->
                    </nav>

            <p>Thanks for the order, we will contact you</p>




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


    $('#menu').click(function(){
        window.location.href='/';
    });

</script>




</body>
</html>
