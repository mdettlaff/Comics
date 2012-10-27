package mdettlaff.comics.service;

import org.springframework.stereotype.Service;

@Service
public class HttpServiceFactory {

	public HttpService getInstance() {
		return new HttpService();
	}
}
