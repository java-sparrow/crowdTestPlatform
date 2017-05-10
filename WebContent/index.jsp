<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>环境测试页面</title>
</head>
<body>
	<h1>环境测试</h1>
	
	<p>你的浏览器为：<strong> <%= request.getHeader("User-Agent") %></strong></p>
	
	<hr>
	
	<p>当前访问路径： <strong><%= request.getServletPath() %></strong></p>
	
	<hr>
	
	<p>getServletInfo: <strong><%= getServletInfo() %></strong></p>
</body>
</html>