<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mdettlaff.comics.domain.FileDownload"%>
<%@page import="mdettlaff.comics.repository.ComicsRepository"%>

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
<%
ComicsRepository repository = new ComicsRepository();
if (repository.getDownloads().isEmpty()) {
%>
		<div>No comics found.</div>
<%
} else {
%>
		<div>
<%
	int index = 0;
	if (request.getParameter("index") != null) {
		index = Integer.parseInt(request.getParameter("index"));
	}
%>
			<a href="/?index=<%=Math.min(index + 1, repository.getDownloads().size() - 1)%>">
				<img src="/image?index=<%=index%>" border="0" />
			</a>
		</div>
		<div>
<%
	for (int i = 0; i < repository.getDownloads().size(); i++) {
		FileDownload download = repository.getDownloads().get(i);
%>
			<%=i + 1%>
			<a href="/?index=<%=i%>">
				<%=download.getName() + "." + download.getContentType().getFileExtension()%>
			</a>
			<%=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(download.getCreationTime())%>
			<br>
<%
	}
%>
		</div>
<%
}
if (repository.getErrors().isEmpty()) {
%>
		<div>
			No errors.
		</div>
<%
} else {
%>
		<div class="error">
<%
	for (String error : repository.getErrors()) {
%>
			Error: <%=error%><br>
<%
	}
%>
		</div>
<%
}
%>
		<div>
			<a href="/download">Download comics now</a>
		</div>
	</body>
</html>
