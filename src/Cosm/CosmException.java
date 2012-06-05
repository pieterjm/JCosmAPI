package Cosm;

import org.apache.http.StatusLine;
import org.json.JSONException;
import org.json.JSONObject;

public class CosmException extends Exception {
	private StatusLine statusLine;
	private String title;
	private String errorMessage;
	
	public CosmException(StatusLine statusLine,String body) {
		this.statusLine = statusLine;
		try {
			JSONObject jo = new JSONObject(body);
			this.title = jo.getString("title");
			this.errorMessage = jo.getString("errors");
		} catch ( JSONException e ) {
			this.title = "Could not parse response body";
			this.errorMessage = body;
		}		
	}
	
	
	
	public CosmException(String errorMessage) {		
		super(errorMessage);
	}
	
	@Override
	public String getMessage() {
		if (( title != null )&&(errorMessage!=null)) {
			return title + ". " + errorMessage;
		}
		if ( errorMessage != null ) {
			return errorMessage;
		}
		if ( statusLine != null ) {
			return statusLine.toString();
		}
		return super.getMessage();		
	}
	

}