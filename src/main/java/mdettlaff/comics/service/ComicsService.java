package mdettlaff.comics.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mdettlaff.comics.domain.Comic;
import mdettlaff.comics.domain.ContentType;
import mdettlaff.comics.domain.FileDownload;
import mdettlaff.comics.repository.ComicsRepository;

public class ComicsService {

	private static final ContentType DEFAULT_CONTENT_TYPE = ContentType.PNG;

	private final ComicsRepository repository;
	private final HttpService httpService;

	public ComicsService(ComicsRepository repository, HttpService httpService) {
		this.repository = repository;
		this.httpService = httpService;
	}

	public void downloadComics() {
		List<Comic> comics = repository.getAllKnownComics();
		repository.clear();
		for (Comic comic : comics) {
			try {
				FileDownload download = downloadComic(comic);
				repository.addDownload(download);
			} catch (Exception e) {
				repository.logError(comic.getName() + ": " + e.getClass() + ": " + e.getMessage());
			}
		}
	}

	private FileDownload downloadComic(Comic comic) throws IOException {
		String imageUrl = getComicImageUrl(comic);
		byte[] imageContent = httpService.download(imageUrl);
		ContentType contentType = getContentTypeByUrl(imageUrl);
		return new FileDownload(comic.getName(), imageContent, contentType);
	}

	private String getComicImageUrl(Comic comic) throws IOException {
		BufferedReader webpageReader = httpService.read(comic.getUrl());
		String currentLine;
		while ((currentLine = webpageReader.readLine()) != null) {
			Pattern pattern = Pattern.compile(comic.getRegexp());
			Matcher matcher = pattern.matcher(currentLine);
			if (matcher.find()) {
				return matcher.group(1);
			}
		}
		throw new RuntimeException("No comic found");
	}

	private ContentType getContentTypeByUrl(String imageUrl) {
		if (imageUrl.matches(".*\\....$")) {
			String fileExtension = imageUrl.substring(imageUrl.length() - 3);
			return ContentType.getByFileExtension(fileExtension);
		}
		return DEFAULT_CONTENT_TYPE;
	}
}
