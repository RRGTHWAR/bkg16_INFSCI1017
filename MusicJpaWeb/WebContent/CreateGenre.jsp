<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Music Genre</title>
</head>
<body>

<%
String genreName;
String genreDescription;
if(request.getParameter("genreName") != null && request.getParameter("genreDescr") != null) {
	genreName = request.getParameter("genreName");
	genreDescription = request.getParameter("genreDescr");
	response.getWriter().println(genreName);
	GenreManager gm = new GenreManager();
}
%>


</body>
</html>