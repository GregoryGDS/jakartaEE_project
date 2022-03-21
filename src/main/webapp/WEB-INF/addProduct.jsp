<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add product</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<h1>Add product</h1>
<div class="container" style="margin-top: 5%">
    <div class="row">
        <div class="offset-3 col-6">
        	<h1>Add product</h1>
			<form action="${pageContext.request.contextPath}/auth/admin/add-product" method="post">
				<div class="mb-3">
				    <label for="name">Name</label>
				    <input id="name" type="text" name="pName">
				 </div>
				 <div class="mb-3">
					<label for="content">Content</label>
					<input id="content" type="text" name="pContent">
				 </div>
			     <div class="mb-3">
					<label for="price">Price</label>
			   		<input id="price" type="number" name="pPrice">
				 </div>
	             <div class="mb-3">
	                 <label for="category">Category</label>
	                 <select class="form-select" aria-label="Default select example" id="category" name="pCategoryId">
	                     <option selected>Select category</option>
	                     <c:forEach items="${categoryList}" var="category">
						 	<option value="${category.id}">${category.name}</option>
				         </c:forEach>
			    	 </select>
				 </div>
			    <div class="d-grid gap-2">
			    	<button type="submit">Add</button>
			    </div>
			</form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
