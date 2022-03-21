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
        <c:if test="${ERROR_TYPE_ID_USER}">
            <h2>Category id must be an integer</h2>
        </c:if>

        <c:if test="${ERROR_UNKNOWN_PERSON}">
            <h2>Category does not exist</h2>
        </c:if>

        <c:if test="${not empty user}">
        <div class="offset-3 col-6">
                <h1>Add product</h1>
                <form action="${pageContext.request.contextPath}/auth/admin/update-user" method="post">
                    <div class="mb-3">
                        <label for="name">Name</label>
                        <input class="form-control" id="name" type="text" name="pName" value="${user.name}">
                    </div>

                    <div class="mb-3">
                        <label for="type">Statut ${user.isValid}</label>
                        <select class="form-select" aria-label="Default select example" id="type" name="pIsValid">
                            <option ${user.isValid ? '' : 'selected'} value="true">Compte active</option>
                            <option ${user.isValid ? '' : 'selected'} value="false">Compte non active</option>
                        </select>
                    </div>
                    <div class="d-grid gap-2">
                        <button class="btn btn-secondary" type="submit">METTRE A JOUR</button>
                    </div>
                </form>
        </div>
        </c:if>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
