package mdettlaff.comics.domain;

public enum ContentType {

	PNG("image/png", "png"),
	GIF("image/gif", "gif");

	private final String mimeType;
	private final String fileExtension;

	private ContentType(String mimeType, String fileExtension) {
		this.mimeType = mimeType;
		this.fileExtension = fileExtension;
	}

	public String getMimeType() {
		return mimeType;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public static ContentType getByFileExtension(String extension) {
		for (ContentType contentType : values()) {
			if (contentType.getFileExtension().equals(extension)) {
				return contentType;
			}
		}
		throw new IllegalArgumentException("No content type for file extension: " + extension);
	}
}
