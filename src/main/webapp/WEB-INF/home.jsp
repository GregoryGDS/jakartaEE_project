<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Home</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<h1>${titlePage}</h1>

<a href="${pageContext.request.contextPath}/list-product">List product</a>
<a href="${pageContext.request.contextPath}/login">Login</a>
<a href="${pageContext.request.contextPath}/auth/basic-insert">Add product</a>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
