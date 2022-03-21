<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product list</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<div class="container" style="margin-top: 5%">
    <div class="row">
        <h1>User list</h1>

        <table class="table">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>isValid</th>
                <th>Basket size</th>
                <th colspan="3">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${!empty userList}">
                    <c:forEach items="${userList}" var="user">
                    <tr class="align-middle">
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.isValid}</td>
                        <td>${user.basket.size()}</td>

                            <td>
                                <a class="btn btn-secondary" href="#">Details</a>
                            </td>
                            <td>
                                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/auth/admin/update-user?id=${user.id}">Update</a>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/auth/admin/remove-user" method="post">
                                    <input hidden name="userId" value="${user.id}">
                                    <button class="btn btn-secondary" type="submit">Remove</button>
                                </form>
                            </td>


                    </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="6"></td>
                        <td colspan="1">
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/add-person">AJOUTER UNE PERSONNE</a>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="6"> </td></tr>
                    <tr>
                        <td colspan="6" style="text-align: center">PAS DE PERSONNES DANS LA LISTE</td>
                    </tr>
                    <tr>
                        <td colspan="6" style="text-align: center"><a href="${pageContext.request.contextPath}/add-person">AJOUTER UNE PERSONNE</a></td>
                    </tr>
                    <tr><td colspan="6"> </td></tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

    </div>

    <div class="row">
        <h1>Admin list</h1>

        <table class="table">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>isValid</th>
                <th colspan="2">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${!empty adminList}">
                    <c:forEach items="${adminList}" var="admin">
                        <tr class="align-middle">
                            <td>${admin.id}</td>
                            <td>${admin.name}</td>
                            <td>${admin.isValid}</td>

                            <td>
                                <a class="btn btn-secondary"
                                   href="#">Details</a>
                                   <!-- ${pageContext.request.contextPath}/product-details?id=${admin.id} -->
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/auth/admin/remove-admin" method="post">
                                    <input hidden name="adminId" value="${admin.id}">
                                    <button class="btn btn-secondary" type="submit">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4"></td>
                        <td colspan="1">
                            <a class="btn btn-secondary" href="/add-person">AJOUTER UNE PERSONNE</a>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="6"> </td></tr>
                    <tr>
                        <td colspan="6" style="text-align: center">PAS DE PERSONNES DANS LA LISTE</td>
                    </tr>
                    <tr>
                        <td colspan="6" style="text-align: center">
                            <a class="btn btn-secondary" href="/add-person">AJOUTER UNE PERSONNE</a>
                        </td>
                    </tr>
                    <tr><td colspan="6"> </td></tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
