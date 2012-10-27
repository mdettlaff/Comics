package mdettlaff.comics.repository;

import java.util.ArrayList;
import java.util.List;

import mdettlaff.comics.domain.Comic;
import mdettlaff.comics.domain.FileDownload;

public class ComicsRepository {

	private static List<FileDownload> downloads = new ArrayList<FileDownload>();
	private static List<String> errors = new ArrayList<String>();

	public List<Comic> getAllKnownComics() {
		List<Comic> comics = new ArrayList<Comic>();
		comics.add(new Comic("Dilbert", "http://www.dilbert.com/fast/", "^<img src=\"(.*?)\" />"));
		comics.add(new Comic("xkcd", "http://xkcd.com/", "embedding\\): (.*?)$"));
		return comics;
	}

	public void clear() {
		downloads.clear();
		errors.clear();
	}

	public void addDownload(FileDownload download) {
		downloads.add(download);
	}

	public List<FileDownload> getDownloads() {
		return downloads;
	}

	public void logError(String error) {
		errors.add(error);
	}

	public List<String> getErrors() {
		return errors;
	}
}
