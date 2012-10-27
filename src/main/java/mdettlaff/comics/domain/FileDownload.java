package mdettlaff.comics.domain;

import java.util.Date;

public class FileDownload {

	private final String name;
	private final byte[] content;
	private final ContentType contentType;
	private final Date creationTime;

	public FileDownload(String name, byte[] content, ContentType contentType) {
		this.name = name;
		this.content = content;
		this.contentType = contentType;
		this.creationTime = new Date();
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

	public Date getCreationTime() {
		return creationTime;
	}
}
