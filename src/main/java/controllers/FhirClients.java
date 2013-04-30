package controllers;
import java.net.URI;
import java.util.HashMap;

import models.Resource;
import resources.ResourceReference;

public class FhirClients {
	HashMap<String, FhirClient> servers = new HashMap<String, FhirClient>();
	
	public void RegisterServer(String baseUrl, FhirClient server) throws FhirClientException {
		if (servers.containsKey(baseUrl))
			throw new FhirClientException("Duplicate Server Id");
		servers.put(baseUrl, server);
	}
	
	public FhirClient resolveServer(ResourceReference ref) {
		return null;
		// todo
	}
	
	public Resource getResource(ResourceReference ref) {
		return null;
		// todo		
	}

	public Resource getResource(URI ref) {
		return null;
		// todo		
	}
}
