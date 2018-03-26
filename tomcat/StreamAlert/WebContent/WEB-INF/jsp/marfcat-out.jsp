<%@ page import="java.util.ArrayList, marfcat.Weakness" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Marfcat Output</title>
</head>
<body>
	<h1>Marfcat Output:</h1>
	<hr>
	<br>
	<h3>Report</h3>
	<h5>Tool Name: <%= (String)request.getAttribute("report-tool_name") %></h5>
	<h5>Tool Version: <%= (String)request.getAttribute("report-tool_version") %></h5>
	<br>
	<hr>
	<br>
	<h3>Weaknesses</h3>
	<% ArrayList<Weakness> weaknesses = (ArrayList<Weakness>)request.getAttribute("weaknesses"); %>
	<% for(Weakness w : weaknesses) { %>
	<hr>
	<h5>Weakness ID: <%= w.id %></h5>
	<h5>Weakness tool specific id: <%= w.toolSpecificId %></h5>
	<h5>Name (cweid: <%= w.name.cweid %>):  <%= w.name.name %></h5>
	<h5>Location ID: <%= w.location.id %></h5>
	<h5>Location line: <%= w.location.line %></h5>
	<h5>Location path: <%= w.location.path %></h5>
	<h5>Location fragment: <%= w.location.fragment %></h5>
	<h5>Location explanation: <%= w.location.explanation %></h5>
	<h5>Grade severity: <%= w.grade.severity %></h5>
	<h5>Grade probability: <%= w.grade.probability %></h5>
	<h5>Grade tool-specific rank: <%= w.grade.toolSpecificRank %></h5>
	<h5>Text output: <%= w.output.textoutput %></h5>
	<% } %>
</body>
</html>