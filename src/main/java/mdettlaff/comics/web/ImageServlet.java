package mdettlaff.comics.web;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mdettlaff.comics.domain.FileDownload;
import mdettlaff.comics.repository.ComicsRepository;

import org.apache.commons.io.IOUtils;

public class ImageServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			int index = Integer.parseInt(request.getParameter("index"));
			ComicsRepository repository = new ComicsRepository();
			FileDownload image = repository.getDownloads().get(index);
			response.addHeader("Content-Type", image.getContentType().getMimeType());
			IOUtils.write(image.getContent(), response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
