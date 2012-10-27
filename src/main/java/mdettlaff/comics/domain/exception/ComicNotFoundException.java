package mdettlaff.comics.domain.exception;

public class ComicNotFoundException extends Exception {

	public ComicNotFoundException(String url) {
		super("No comic image found on URL: " + url);
	}
}
