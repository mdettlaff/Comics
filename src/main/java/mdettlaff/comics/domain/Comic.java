package mdettlaff.comics.domain;

import java.util.regex.Pattern;

public class Comic {

	private final String name;
	private final String url;
	private final Pattern imageUrlPattern;

	public Comic(String name, String url, String imageUrlPattern) {
		this.name = name;
		this.url = url;
		this.imageUrlPattern = Pattern.compile(imageUrlPattern);
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public Pattern getImageUrlPattern() {
		return imageUrlPattern;
	}
}
