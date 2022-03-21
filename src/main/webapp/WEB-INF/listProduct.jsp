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
        <h1>Product list</h1>

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
                <c:when test="${!empty productList}">
                    <c:forEach items="${productList}" var="product">
                    <tr class="align-middle">
                        <td>${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.content}</td>
                        <td>${product.category.name}</td>
                        <td>${product.price}</td>
                        <c:if test="${sessionScope.type == 'admin'}">
                            <td><a class="btn btn-secondary"
                                   href="${pageContext.request.contextPath}/product-details?id=${product.id}">Details</a>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/auth/admin/remove-product" method="post">
                                    <input hidden name="productId" value="${product.id}">
                                    <button class="btn btn-secondary" type="submit">Remove</button>
                                </form>
                            </td>
                        </c:if>
                        <c:if test="${sessionScope.type == 'user'}">
                            <td>
                                <form action="${pageContext.request.contextPath}/auth/add-to-basket" method="post">
                                    <input hidden name="productId" value="${product.id}">
                                    <button class="btn btn-secondary" type="submit">Add to basket</button>
                                </form>
                            </td>
                        </c:if>
                        <c:if test="${empty sessionScope.type }">
                            <!-- Pas d'actions -->
                            <td>Must be connected !</td>
                        </c:if>

                    </tr>
                    </c:forEach>
                    <c:if test="${sessionScope.type == 'admin'}">
                    <tr>
                        <td colspan="6"></td>
                        <td>
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/auth/admin/add-product">AJOUTER UN PRODUIT</a>
                        </td>
                    </tr>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="6"> </td></tr>
                    <tr>
                        <td colspan="6" style="text-align: center">PAS DE PRODUITS DANS LA LISTE</td>
                    </tr>
                    <tr>
                        <td colspan="6" style="text-align: center">
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/auth/admin/add-product">AJOUTER UN PRODUIT</a>
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
