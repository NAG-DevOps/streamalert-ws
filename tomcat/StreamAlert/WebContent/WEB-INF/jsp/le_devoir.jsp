<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Le Devoir XML parsing</title>
</head>
<body>
	<h2>Content of all "title" elements from Le Devoir's RSS feed:</h2> 
	<c:forEach items="${parsedStrings}" var="parsedString">
		${parsedString} <br>
	</c:forEach>
</body>
</html>