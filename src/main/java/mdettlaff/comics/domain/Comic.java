package mdettlaff.comics.domain;

public class Comic {

	private final String name;
	private final String url;
	private final String regexp;

	public Comic(String name, String url, String regexp) {
		this.name = name;
		this.url = url;
		this.regexp = regexp;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getRegexp() {
		return regexp;
	}
}
