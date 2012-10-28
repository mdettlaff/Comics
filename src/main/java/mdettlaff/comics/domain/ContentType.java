package mdettlaff.comics.domain;

import org.springframework.http.MediaType;

public enum ContentType {

	PNG(MediaType.IMAGE_PNG, "png"),
	GIF(MediaType.IMAGE_GIF, "gif"),
	JPG(MediaType.IMAGE_JPEG, "jpg");

	private final MediaType mediaType;
	private final String fileExtension;

	private ContentType(MediaType mediaType, String fileExtension) {
		this.mediaType = mediaType;
		this.fileExtension = fileExtension;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public static ContentType getByFileExtension(String extension) {
		for (ContentType contentType : values()) {
			if (contentType.getFileExtension().equalsIgnoreCase(extension)) {
				return contentType;
			}
		}
		throw new IllegalArgumentException("No content type for file extension: " + extension);
	}
}
