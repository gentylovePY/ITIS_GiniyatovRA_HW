<%--
  Created by IntelliJ IDEA.
  User: romanginiatov
  Date: 17.10.2022
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="home.css">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#btn").click(function () {
                var user =  $("#username").val()
                console.log(user)
                $.ajax({
                    type: 'POST',
                    url: "ProfServlet",
                    data: $("form").serialize(),
                    success: function (result) {
                        $("#h5").text(result);
                        console.log(result);
                    },
                    error: function (result) {
                        console.log(result);
                    }
                });
            });
        });
    </script>
</head>
<body>
<%
    String name = (String) session.getAttribute("username");

    if (name == null){


%>
<div class="container">
    <nav>
        <ul>

            <li style="float: right;"><a href="login">Login</a></li>
            <li style="float: right;"><a href="reg">Registration</a></li>

        </ul>
    </nav>
</div>
<%
}else if (name != null){


%>
<form action="ProfServlet" method="post">


<div class="container">
    <nav>
        <ul>

            <li style="float: right;"><a href="logout">Log out</a></li>
            <h5 href="profile" style="float: right; color: white; font-size: 20px">Welcome, <%=name%></h5>

        </ul>
    </nav>
    <label ><b>погода</b></label>
    <input type="text" placeholder="город" name="ci">
    <br>
    <br>
    <h5 id="h5"></h5>
    <button type="button" id="btn" class="city">get</button>
</div>
</form>
<%
    }%>
</body>
</html>
