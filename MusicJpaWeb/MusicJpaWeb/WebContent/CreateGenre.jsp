<%@page import="java.util.UUID"%>
<%@page import="music.*" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Music Genre</title>
</head>
<body>

<% 
String genreName = "";
String genreDescr = "";
if(request.getParameter("genreName") != null && request.getParameter("genreDescr") != null){
	genreName = request.getParameter("genreName");
	genreDescr = request.getParameter("genreDescr");
	response.getWriter().println(genreName);
	GenreManager gm = new GenreManager();
	
	gm.createGenre(UUID.randomUUID().toString(), genreName, genreDescr);
}
%>

<b><%= genreName %></b>
</body>
</html>