<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product list</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<c:set var="count" value="0" scope="page" />

<div class="container" style="margin-top: 5%">
    <div class="row">
        <h1>Your shopping basket</h1>

        <table class="table">
            <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Content</th>
                <th>Category</th>
                <th>Price</th>
                <th colspan="2">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${!empty basket}">
                    <c:forEach items="${basket}" var="b">
                        <tr class="align-middle">
                            <td>${b.id}</td>
                            <td>${b.name}</td>
                            <td>${b.content}</td>
                            <td>${b.category.name}</td>
                            <td>${b.price}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/auth/remove-from-basket" method="post">
                                    <input hidden name="productId" value="${b.id}">
                                    <input hidden name="indexList" value="${count}">
                                    <c:set var="count" value="${count + 1}" scope="page" />
                                    <button class="btn btn-secondary" type="submit">Remove from basket</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5"></td>
                        <td><a class="btn btn-secondary" href="${pageContext.request.contextPath}/pay-basket">PAYER</a></td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="6"> </td></tr>
                    <tr>
                        <td colspan="6" style="text-align: center">PAS DE PRODUITS DANS LE PANIER</td>
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