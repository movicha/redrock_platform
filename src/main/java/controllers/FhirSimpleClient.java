package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Map;

import formats.XmlComposer;
import formats.XmlParser;
import models.AtomEntry;
import models.AtomEntryImpl;
import models.AtomFeed;
import models.Conformance;
import models.Resource;
import resources.ResourceType;

/**
 * no security. no proxy
 */
public class FhirSimpleClient implements FhirClient {

	private String baseUrl;
	
	public FhirSimpleClient(String baseUrl) throws MalformedURLException {
		super();
		this.baseUrl = baseUrl;
	}

	private HttpURLConnection makeClient(String tail) throws Exception {
		HttpURLConnection client = (HttpURLConnection) new URL(baseUrl+tail).openConnection();
		client.addRequestProperty("accept", "text/xml");
		return client;
	}
	
	
	@Override
	public Conformance getConformanceStatement() throws FhirClientException {
		try {
			URLConnection client = makeClient("/metadata");
			return (Conformance) new XmlParser().parse(client.getInputStream());
		} catch (Exception e) {
			throw new FhirClientException(e);
		}
	}

	@Override
	public AtomEntry read(ResourceType type, String id) throws FhirClientException {
		try {
			URLConnection client = makeClient("/"+type.toString().toLowerCase()+"/@"+id);
			Resource r = new XmlParser().parse(client.getInputStream());
			AtomEntry e = new AtomEntryImpl();
			e.setCategory(r.getResourceType().toString());
			e.setUpdated(javax.xml.bind.DatatypeConverter.parseDateTime(client.getHeaderField("Last-Updated")));
			e.setId(id);
			e.getLinks().put("self", client.getHeaderField("Content-Location"));
			e.setResource(r);
			return e;
		} catch (Exception e) {
			throw new FhirClientException(e);
		}
	}

	@Override
	public AtomEntry vread(ResourceType type, String id, String versionid) throws FhirClientException {
//		try {
//			URLConnection client = makeClient("/"+type.toString().toLowerCase()+"/@"+id+"/history/@"+versionid);
//			return new XmlParser().parse(client.getInputStream());
//		} catch (Exception e) {
//			throw new FhirClientException(e);
//		}
		throw new FhirClientException("not implemented yet");
	}

	@Override
	public AtomEntry update(String id, AtomEntry resource) throws FhirClientException {
		throw new FhirClientException("not implemented yet");
//		try {
//			HttpURLConnection client = makeClient("/"+resource.getResourceType().toString().toLowerCase()+"/@"+id);
//			client.setRequestMethod("PUT");
//			client.setDoOutput(true); // Triggers POST.
//			client.setRequestProperty("Content-Type", "text/xml+fhir;charset=UTF-8");
//			OutputStream output = null;
//			try {
//				output = client.getOutputStream();
//				new XmlComposer().compose(output, resource, false);
//			} finally {
//				if (output != null) 
//					output.close();  
//			}
//			return new XmlParser().parse(client.getInputStream());		
//		} catch (Exception e) {
//			throw new FhirClientException(e);
//		}
	}

	@Override
	public void delete(ResourceType type, String id)
			throws FhirClientException {
		// TODO Auto-generated method stub

	}

	@Override
	public String create(AtomEntry resource) throws FhirClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed history(Calendar lastUpdate, ResourceType type, String id)
			throws FhirClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed history(Calendar lastUpdate, ResourceType type)
			throws FhirClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed history(Calendar lastUpdate) throws FhirClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed search(ResourceType type, Map<String, String> params)
			throws FhirClientException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AtomFeed batch(AtomFeed batch) throws FhirClientException {
		// TODO Auto-generated method stub
		return null;
	}
}
