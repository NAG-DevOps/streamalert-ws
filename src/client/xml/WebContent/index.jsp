<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Web Client for XML Reading</title>
</head>
<body>

<form action="ReaderServlet" method="POST">

	<div>
		<label>Markup: </label>
		<select name="markup">
		  <option value="0">RSS</option>
		  <option value="1">NN</option>
		  <option value="2">MAFRCATIN</option>
		  <option value="3">MAFRCATOUT</option>
		  <option value="4">WSDL</option>
		</select>
	</div>
	
	<div>
		<label>Parser: </label>
		<select name="parser" >
		  <option value="0">DOM</option>
		  <option value="1">SAX</option>
		</select>
	</div>
	
	<div>
		<label>URI: </label>
		<input type="text" name="uri" placeholder="Optional URI"/>
	</div>
	
	<div>
		<label>Search Term: </label>
		<input type="text" name="searchTerm" placeholder="Optional Search Term"/>
	</div>
	
	<input type="submit" value="Parse XML">
	
</form>

</body>
</html>