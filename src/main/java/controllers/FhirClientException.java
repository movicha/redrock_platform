package controllers;

public class FhirClientException extends Exception {
	private static final long serialVersionUID = 1L;

	public FhirClientException(String string) {
		super(string);
	}

	public FhirClientException(Exception e) {
		super(e);
	}
}