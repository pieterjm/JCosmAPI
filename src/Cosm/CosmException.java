package Cosm;

public class CosmException extends Exception {
	
	/**
	 * Error message: This is a HTTP status code retrieved from a failed http request
	 */
	public String errorMessage;

	public CosmException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	

}