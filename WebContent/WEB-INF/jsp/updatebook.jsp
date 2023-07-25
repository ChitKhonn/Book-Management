<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div style='color:red'>${error}</div>
<div style='color:red'>${msg}</div>
	<form:form action="/bookmanagement/updatebook" method="post" modelAttribute="bean">
		<table>
			<tr>
				
				<td>Book Code</td>
				<td><form:input type="text" path="bookCode" /> </td>
				<td><form:errors path="bookCode"  style='color:red'></form:errors></td>
			</tr>
			<tr>
				<td>Book Title</td>
				<td><form:input type="text" path="bookTitle" /></td>
				<td><form:errors path="bookTitle"  style='color:red'></form:errors></td>
			</tr>
			<tr>
				<td>Book Author</td>
				<td><form:input type="text" path="bookAuthor" /></td>
				<td><form:errors path="bookAuthor"  style='color:red'></form:errors></td>
			</tr>
			<tr>
				<td>Book Price</td>
				<td><form:input type="text" path="bookPrice" /></td>
				<td><form:errors path="bookPrice"  style='color:red'></form:errors></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Update"/> </td>
			</tr>
		</table>
	</form:form>
</body>
</html>