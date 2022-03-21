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
        <h1>Category list</h1>

        <table class="table">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th colspan="2">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${!empty categoryList}">
                    <c:forEach items="${categoryList}" var="category">
                        <tr>
                            <td>${category.id}</td>
                            <td>${category.name}</td>
                            <td><a href="${pageContext.request.contextPath}/auth/admin/category-details?id=${category.id}">Details</a></td>
                            <td>
                                <form action="${pageContext.request.contextPath}/auth/admin/remove-category" method="post">
                                    <input hidden name="categoryId" value="${category.id}">
                                    <button class="btn btn-secondary" type="submit">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="3"> </td></tr>
                    <tr>
                        <td colspan="3" style="text-align: center">PAS DE CATEGORIES DANS LA LISTE</td>
                    </tr>
                    <tr>
                        <td colspan="3" style="text-align: center"><a href="${pageContext.request.contextPath}/auth/admin/add-category">AJOUTER UNE CATEGORIE</a></td>
                    </tr>
                    <tr><td colspan="3"> </td></tr>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>

    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>