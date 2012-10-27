package mdettlaff.comics.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import mdettlaff.comics.domain.Comic;
import mdettlaff.comics.domain.ContentType;
import mdettlaff.comics.domain.FileDownload;
import mdettlaff.comics.repository.ComicsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class ComicsServiceTest {

	private static final String XKCD_IMAGE_URL = "http://imgs.xkcd.com/comics/epsilon_and_zeta.png";
	private static final String DILBERT_IMAGE_URL = "http://www.dilbert.com/dyn/str_strip/000000000/00000000/0000000/100000/60000/9000/900/169992/169992.strip.print.gif";
	private static final String ERROR_MESSAGE = "Hamster Huey: ComicNotFoundException: No comic image found on URL: http://www.hamsterhuey.com/";

	private ComicsService service;

	private ComicsRepository repository;
	private HttpServiceFactory httpServiceFactory;

	@Before
	public void setUp() {
		repository = mock(ComicsRepository.class);
		httpServiceFactory = mock(HttpServiceFactory.class);
		service = new ComicsService(repository, httpServiceFactory);
	}

	@Test
	public void testDownloadComics() throws Exception {
		// Prepare
		BufferedReader xkcdWebpageReader =
			new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("xkcd.html")));
		BufferedReader dilbertWebpageReader =
			new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("dilbert.html")));
		BufferedReader hamsterHueyWebpageReader = new BufferedReader(new StringReader("foo\nbar"));
		// Mock
		when(repository.getAllKnownComics()).thenReturn(getComics());
		HttpService httpService = mock(HttpService.class);
		when(httpService.read("http://xkcd.com/")).thenReturn(xkcdWebpageReader);
		when(httpService.download(XKCD_IMAGE_URL)).thenReturn("xkcd image".getBytes());
		when(httpService.read("http://www.dilbert.com/fast/")).thenReturn(dilbertWebpageReader);
		when(httpService.download(DILBERT_IMAGE_URL)).thenReturn("dilbert image".getBytes());
		when(httpService.read("http://www.hamsterhuey.com/")).thenReturn(hamsterHueyWebpageReader);
		when(httpServiceFactory.getInstance()).thenReturn(httpService);
		// Test
		service.downloadComics();
		// Verify
		verify(repository).clear();
		verify(httpService).closeReader(same(xkcdWebpageReader));
		verify(httpService).closeReader(same(dilbertWebpageReader));
		ArgumentCaptor<FileDownload> fileDownloadArgument = ArgumentCaptor.forClass(FileDownload.class);
		verify(repository, times(2)).addDownload(fileDownloadArgument.capture());
		FileDownload xkcdFileDownload = fileDownloadArgument.getAllValues().get(0);
		assertEquals("xkcd", xkcdFileDownload.getName());
		assertEquals("xkcd image", new String(xkcdFileDownload.getContent()));
		assertEquals(ContentType.PNG, xkcdFileDownload.getContentType());
		FileDownload dilbertFileDownload = fileDownloadArgument.getAllValues().get(1);
		assertEquals("Dilbert", dilbertFileDownload.getName());
		assertEquals("dilbert image", new String(dilbertFileDownload.getContent()));
		assertEquals(ContentType.GIF, dilbertFileDownload.getContentType());
		verify(repository).logError(ERROR_MESSAGE);
	}

	private List<Comic> getComics() {
		List<Comic> comics = new ArrayList<Comic>();
		comics.add(new Comic("xkcd", "http://xkcd.com/", "embedding\\): (.*?)$"));
		comics.add(new Comic("Hamster Huey", "http://www.hamsterhuey.com/", "<img src=\"(.*?)\""));
		comics.add(new Comic("Dilbert", "http://www.dilbert.com/fast/", "^<img src=\"(.*?)\" />"));
		return comics;
	}
}
