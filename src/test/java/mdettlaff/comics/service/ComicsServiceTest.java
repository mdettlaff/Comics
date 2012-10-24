package mdettlaff.comics.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import mdettlaff.comics.domain.ContentType;
import mdettlaff.comics.domain.FileDownload;
import mdettlaff.comics.repository.ComicsRepository;

import org.junit.Before;
import org.junit.Test;

public class ComicsServiceTest {

	private ComicsService service;

	private ComicsRepository repository;
	private HttpService httpService;

	@Before
	public void setUp() {
		repository = new ComicsRepository();
		httpService = new HttpService();
		service = new ComicsService(repository, httpService);
	}

	@Test
	public void testDownloadComics() {
		service.downloadComics();
		assertNotNull(repository.getDownloads());
		assertEquals(1, repository.getDownloads().size());
		FileDownload download = repository.getDownloads().get(0);
		assertEquals("xkcd", download.getName());
		assertEquals("xkcd_content", new String(download.getContent()));
		assertEquals(ContentType.PNG, download.getContentType());
		assertEquals(1, repository.getErrors().size());
	}
}
