<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Genre Form</title>
</head>
<body>
<form method="post" name="frmCreateGenre" id="frmCreateGenre" action="servlets/CreateGenre">
	<p>
	Genre name: 
	<input type="text" value="" name="genreName" />
	</p>
	<p>
	Genre description:
	<textarea cols="50" rows="5" name="genreDescr"></textarea>
	</p>
	<input type="submit" name="btnSubmit" value="Save" />
</form>
</body>
</html>