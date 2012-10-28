package mdettlaff.comics.web;

import java.util.HashMap;
import java.util.Map;

import mdettlaff.comics.domain.FileDownload;
import mdettlaff.comics.service.ComicsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ComicsController {

	private final ComicsService comicsService;

	@Autowired
	public ComicsController(ComicsService comicsService) {
		this.comicsService = comicsService;
	}

	@RequestMapping("")
	public ModelAndView home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("images", comicsService.getImages());
		model.put("errors", comicsService.getErrors());
		return new ModelAndView("index", model);
	}

	@RequestMapping("/download")
	public String download() {
		comicsService.downloadComics();
		return "download_finished";
	}

	@RequestMapping("/image/{index}")
	public ResponseEntity<byte[]> image(@PathVariable("index") int index) {
		FileDownload image = comicsService.getImages().get(index);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(image.getContentType().getMediaType());
		return new ResponseEntity<byte[]>(image.getContent(), headers, HttpStatus.CREATED);
	}
}
