<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Details</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<div class="container" style="margin-top: 5%">
    <div class="row" style="text-align: center">

        <c:if test="${ERROR_TYPE_ID_CATEGORY}">
            <h2>Category id must be an integer</h2>
        </c:if>

        <c:if test="${ERROR_UNKNOWN_CATEGORY}">
            <h2>Category does not exist</h2>
        </c:if>

        <c:if test="${not empty category}">
            <table class="table">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                </tr>
                </thead>
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>

                </tr>
            </table>

            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/auth/admin/list-category">GO BACK</a>
        </c:if>
    </div>
</div>


<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
