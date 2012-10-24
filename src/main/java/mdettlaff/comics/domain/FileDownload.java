package mdettlaff.comics.domain;

public class FileDownload {

	private final String name;
	private final byte[] content;
	private final ContentType contentType;

	public FileDownload(String name, byte[] content, ContentType contentType) {
		this.name = name;
		this.content = content;
		this.contentType = contentType;
	}

	public String getName() {
		return name;
	}

	public byte[] getContent() {
		return content;
	}

	public ContentType getContentType() {
		return contentType;
	}
}
