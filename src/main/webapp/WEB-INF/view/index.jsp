<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mdettlaff.comics.domain.FileDownload"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<style>
			a {
				text-decoration: none;
			}
			.error {
				color: red;
			}
		</style>
	</head>
	<body>

<c:if test="${empty images}">
		<div>No comics found.</div>
</c:if>
<c:if test="${not empty images}">
		<div>
<%
	int index = 0;
	if (request.getParameter("index") != null) {
		index = Integer.parseInt(request.getParameter("index"));
	}
	List<FileDownload> images = (List<FileDownload>)request.getAttribute("images");
%>
			<a href="/?index=<%=Math.min(index + 1, images.size() - 1)%>">
				<img src="/image/<%=index%>" border="0" />
			</a>
		</div>
		<div>
<%
	for (int i = 0; i < images.size(); i++) {
		FileDownload download = images.get(i);
%>
			<%=i + 1%>
			<a href="/?index=<%=i%>"><%=download.getName()%></a>
			<%=download.getContentType().getFileExtension()%>
			<%=download.getContent().length / 1000%>K
			<%=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(download.getCreationTime())%>
			<br>
<%
	}
%>
		</div>
</c:if>

<c:if test="${empty errors}">
		<div>
			No errors.
		</div>
</c:if>
<c:if test="${not empty errors}">
		<div class="error">
	<c:forEach items="#{errors}" var="error">
			Error: ${error}<br>
	</c:forEach>
		</div>
</c:if>

		<div>
			<a href="/download">Download comics now</a>
		</div>

	</body>
</html>
