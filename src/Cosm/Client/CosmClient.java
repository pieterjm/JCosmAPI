package Cosm.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.ws.Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;


public class CosmClient {
	private HttpClient client;
	private AuthMethod authMethod;
	private String API_KEY;
	private String username;
	private String password;

	public CosmClient(String APIKEYS) {
		this.client = new DefaultHttpClient();
		this.authMethod = AuthMethod.apikey;
		this.API_KEY = APIKEYS;
	}

	public CosmClient(String username,String password) {
		this.client = new DefaultHttpClient();
		this.authMethod = AuthMethod.userpw;
		this.username = username;
		this.password = password;
	}
		
	public HttpResponse execute(HttpRequestBase request) throws IOException {
		switch ( authMethod ) {
		case apikey:
			request.addHeader("X-PachubeApiKey", this.API_KEY);
			break;
		case none:
			break;
		case userpw:
			String authString = this.username + ":" + this.password;
			request.addHeader("Authorization", "Basic " + Base64.encodeBytes(authString.getBytes()));
			break;
		}
		return client.execute(request);
	}
		
	public String getBody(HttpResponse response) throws IOException {
		StringBuilder builder = new StringBuilder();				
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		return builder.toString();		
	}

	
}
