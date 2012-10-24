package mdettlaff.comics.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpService {

	public BufferedReader read(String url) {
		InputStream testResource = getClass().getResourceAsStream("xkcd");
		return new BufferedReader(new InputStreamReader(testResource));
	}

	public byte[] download(String url) {
		return "xkcd_content".getBytes();
	}
}
