package controllers;

import java.util.Calendar;
import java.util.Map;

import models.AtomEntry;
import models.AtomFeed;
import models.Conformance;
import models.Resource;
import resources.ResourceType;

/**
 * Have one interface for each particular server
 */
public interface FhirClient {

	// Get a conformance statement for the system
	public Conformance getConformanceStatement() throws FhirClientException;
	
	// Read the current state of a resource
	public AtomEntry read(ResourceType type, String id) throws FhirClientException;
	
	// Read the state of a specific version of the resource
	public AtomEntry vread(ResourceType type, String id, String versionid) throws FhirClientException; 
	
    // Update an existing resource by its id (or create it if it is new)
	public AtomEntry update(String id, AtomEntry resource) throws FhirClientException;
	
	// Delete a resource
	public void delete(ResourceType type, String id) throws FhirClientException; 

	// Create a new resource with a server assigned id. return the id the server assigned
	public String create(AtomEntry resource) throws FhirClientException;
	
	// Retrieve the update history for a resource, for a resource type, for all resources. LastUpdate can be null for all of these
	public AtomFeed history(Calendar lastUpdate, ResourceType type, String id) throws FhirClientException;
	public AtomFeed history(Calendar lastUpdate, ResourceType type) throws FhirClientException;
	public AtomFeed history(Calendar lastUpdate) throws FhirClientException;
	
	// Search the resource type based on some filter criteria
	public AtomFeed search(ResourceType type, Map<String, String> params) throws FhirClientException;
	
	// 	Update or create a set of resources
	public AtomFeed batch(AtomFeed batch) throws FhirClientException;
	
}
