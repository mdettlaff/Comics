package mdettlaff.comics.web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mdettlaff.comics.repository.ComicsRepository;
import mdettlaff.comics.service.ComicsService;
import mdettlaff.comics.service.HttpServiceFactory;

public class DownloadServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			ComicsRepository repository = new ComicsRepository();
			HttpServiceFactory httpServiceFactory = new HttpServiceFactory();
			ComicsService comicsService = new ComicsService(repository, httpServiceFactory);
			comicsService.downloadComics();
			response.addHeader("Content-Type", "text/html");
			response.getWriter().append("Download successful<br>");
			response.getWriter().append("<a href=\"/\">See downloaded comics</a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
