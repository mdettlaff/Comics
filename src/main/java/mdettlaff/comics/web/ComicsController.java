package mdettlaff.comics.web;

import java.util.HashMap;
import java.util.Map;

import mdettlaff.comics.service.ComicsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
}
