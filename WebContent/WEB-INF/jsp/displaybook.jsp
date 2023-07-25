<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	table,tr,th,td{
		text-align:center;
		border:1px solid blue;
		border-collapse:collapse;
	}
</style>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>

	<table>
		<tr>
			<th>Book Code</th>
			<th>Book Title</th>
			<th>Book Author</th>
			<th>Book Price</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${list}" var="data">
			<tr>
				<td>${data.bookCode}</td>
				<td>${data.bookTitle}</td>
				<td>${data.bookAuthor}</td>
				<td>${data.bookPrice}</td>
				<td>
					<a href="setupupdatebook/${data.bookCode}">Update</a>|
					<a href="deletebook/${data.bookCode}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>