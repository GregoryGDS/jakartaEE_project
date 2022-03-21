<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>

    <jsp:include page="header.jsp"></jsp:include>

    <div class="container">
        <div class="row" style="margin-top: 10%">
            <div class="offset-3 col-6"><h1>LOGIN</h1></div>
        </div>
        <div class="row">
            <div class="offset-3 col-6">
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="mb-3">
                        <label for="usernameInput" class="form-label">Identifiant</label>
                        <input class="form-control" id="usernameInput" type="text" name="username" placeholder="Username">
                    </div>
                    <div class="d-grid gap-2">
                        <button type="submit" class="btn btn-secondary">Connexion</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
