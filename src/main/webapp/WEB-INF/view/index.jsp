<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mdettlaff.comics.domain.FileDownload"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Comics</title>
		<link rel="stylesheet" href="/resources/style.css" type="text/css">
	</head>
	<body>

<c:if test="${empty images}">
		<div>No comics found.</div>
</c:if>
<c:if test="${not empty images}">
		<div>
			<a href="/comic/${nextComicIndex}">
				<img src="/image/${comicIndex}" border="0">
			</a>
		</div>
		<div>
	<c:forEach items="${images}" var="image" varStatus="status">
			${status.count}
			<a href="/comic/${status.count}">${image.name}</a>
			${image.contentType.fileExtension}
			<fmt:formatNumber value="${fn:length(image.content) / 1000}" maxFractionDigits="0" />K
			<fmt:formatDate value="${image.creationTime}" pattern="yyyy.MM.dd HH:mm:ss" />
			<br>
	</c:forEach>
		</div>
</c:if>

<c:if test="${empty errors}">
		<div>
			No errors.
		</div>
</c:if>
<c:if test="${not empty errors}">
		<div class="error">
	<c:forEach items="${errors}" var="error">
			Error: ${error}<br>
	</c:forEach>
		</div>
</c:if>

		<form action="/download" method="post">
			<input type="submit" value="Download comics now">
		</form>

	</body>
</html>
