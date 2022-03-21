<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add product</title>

</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="container" style="margin-top: 5%">
    <div class="row">
        <div class="offset-3 col-6">
                <h1>Add category</h1>
                <form action="${pageContext.request.contextPath}/auth/admin/add-category" method="post">
                    <div class="mb-3">
                        <label for="name">Name</label>
                        <input class="form-control" id="name" type="text" name="cName">
                    </div>
                    <div class="d-grid gap-2">
                        <button class="btn btn-secondary" type="submit">AJOUTER</button>
                    </div>
                </form>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
