package formats;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.apache.commons.codec.binary.Base64;

import models.AtomEntry;
import models.AtomFeed;
import models.Resource;
import utilities.Binary;
import utilities.xhtml.XhtmlComposer;
import utilities.xhtml.XhtmlNode;
import utilities.json.JsonWriter;

public abstract class JsonComposerBase extends XmlBase {
	protected JsonWriter json;
	private boolean htmlPretty;

	public void compose(OutputStream stream, Resource resource) throws Exception {
		OutputStreamWriter osw = new OutputStreamWriter(stream, "UTF-8");
		JsonWriter writer = new JsonWriter(osw);
		// writer.setPretty(pretty);
		writer.object();
		compose(writer, resource);
		writer.endObject();
		osw.flush();
	}

	public void compose(OutputStream stream, AtomFeed feed) throws Exception {
		OutputStreamWriter osw = new OutputStreamWriter(stream, "UTF-8");
		JsonWriter writer = new JsonWriter(osw);
		// writer.setPretty(pretty);
		writer.object();
		compose(writer, feed);
		writer.endObject();
		osw.flush();
	}

	public void compose(JsonWriter writer, Resource resource) throws Exception {
		json = writer;
		composeResource(resource);
	}

	public void compose(JsonWriter writer, AtomFeed feed) throws Exception {
		json = writer;
		composeFeed(feed);
	}

    // standard order for round-tripping examples succesfully:
    // title, id, links, updated, published, authors
	private void composeFeed(AtomFeed feed) throws Exception {
		prop("title", feed.getTitle());
	    prop("id", feed.getId());
	    if (feed.getLinks().size() > 0) {
	      openArray("links");
	      for (String n : feed.getLinks().keySet()) {
	        json.object();
	        prop("rel", n);
	        prop("href", feed.getLinks().get(n));
	        json.endObject();
	      }
	      closeArray();
	    }
		if (feed.getUpdated() != null)
			prop("updated", dateToXml(feed.getUpdated()));

		if (feed.getAuthorName() != null || feed.getAuthorUri() != null) {
		  openArray("authors");
		  json.object();
		  if (feed.getAuthorName() != null)
		    prop("name", feed.getAuthorName());
		  if (feed.getAuthorUri() != null)
		    prop("uri", feed.getAuthorUri());
		  json.endObject();
		  closeArray();
		}

		if (feed.getEntryList().size() > 0) {
			openArray("entries");
			for (AtomEntry e : feed.getEntryList())
				composeEntry(e);
			closeArray();
		}
	}

	// standard order for round-tripping examples succesfully:
	// title, id, links, updated, published, authors 
	private void composeEntry(AtomEntry e) throws Exception {
		json.object();
		prop("title", e.getTitle());
		prop("id", e.getId());
		if (e.getLinks().size() > 0) {
		  openArray("links");
		  for (String n : e.getLinks().keySet()) {
		    json.object();
		    prop("rel", n);
		    prop("href", e.getLinks().get(n));
		    json.endObject();
		  }
		  closeArray();
		}

		if (e.getUpdated() != null)
			prop("updated", dateToXml(e.getUpdated()));
		if (e.getPublished() != null) 
			prop("published", dateToXml(e.getPublished()));

 	    if (e.getAuthorName() != null || e.getAuthorUri() != null) {
	       openArray("authors");
	       json.object();
	       if (e.getAuthorName() != null)
	          prop("name", e.getAuthorName());
	       if (e.getAuthorUri() != null)
	          prop("uri", e.getAuthorUri());
	       json.endObject();
	       closeArray();
	    }

		if (e.getCategory() != null) {
			openArray("categories");
			json.object();
			prop("term", e.getCategory());
			prop("scheme", "http://hl7.org/fhir/resource-types");
			json.endObject();
			closeArray();
		}

		open("content");
		composeResource(e.getResource());
		close();
		if (e.getSummary() != null) {
		  composeXhtml("summary", e.getSummary());
		}
		json.endObject();  
	}

	protected abstract void composeResource(Resource resource) throws Exception;

//	protected void composeElement(Element element) throws Exception {
//		if (element.getXmlId() != null) 
//			prop("_id", element.getXmlId());
//	}
//
	protected void prop(String name, String value) throws Exception {
		if (name != null)
			json.key(name);
		json.value(value);
	}

//	protected void composeType(Type type) throws Exception {
//		composeElement(type);
//	}

//	protected void composeStringSimple(String name, String value) throws Exception {
//		if (value != null)
//			prop(name, value);
//	}
//
//	protected void composeStringSimple(String name, String value) throws Exception {
//		if (value != null)
//			composeStringSimple(name, value.getValue());
//	}
//
//	protected void composeStringSimple(String name, Code value) throws Exception {
//		if (value != null)
//			prop(name, value.getValue());
//	}
//
//	protected void composeCodeSimple(String name, Code value) throws Exception {
//		if (value != null)
//			prop(name, value.getValue());
//	}
//
//	 protected void composeString(String name, String value) throws Exception {
//		if (value != null)
//			prop(name, value);
//	}
//	protected void composeURI(String name, java.net.URI value) throws Exception {
//		if (value != null)
//			prop(name, value.toString());
//	}
//
//	protected void composeBigDecimal(String name, BigDecimal value) throws Exception {
//		if (value != null)
//			prop(name, value.toString());
//	}
//
//
//	protected void composeBigDecimalSimple(String name, Decimal value) throws Exception {
//		if (value != null)
//			composeBigDecimal(name, value.getValue());
//	}
//
//
//	protected void composeInt(String name, java.lang.Integer value) throws Exception {
//		if (value != null)
//			prop(name, value.toString());
//	}
//
//	protected void composeIntSimple(String name, Integer value) throws Exception {
//		if (value != null)
//			composeInt(name, value.getValue());
//	}
//
//	protected void composeBool(String name, java.lang.Boolean value) throws Exception {
//		if (value != null)
//			prop(name, value.toString());
//	}
//
//	protected void composeXhtmlSimple(String name, XhtmlNode html) throws Exception {
//		composeXhtml(name, html);
//	}
//	
	protected void composeXhtml(String name, XhtmlNode html) throws Exception {
		XhtmlComposer comp = new XhtmlComposer();
		comp.setPretty(htmlPretty);
		prop(name, comp.compose(html));
	}

//	protected void composeBytes(String name, byte[] content) throws Exception {
//		if (content != null) {
//			byte[] encodeBase64 = Base64.encodeBase64(content);
//			composeString(name, new String(encodeBase64));
//		}
//	}  
//
//	protected void composeBytesSimple(String name, Base64Binary content) throws Exception {
//		if (content != null) {
//			byte[] encodeBase64 = Base64.encodeBase64(content.getValue());
//			composeString(name, new String(encodeBase64));
//		}
//	}  
//
//	protected void composeBase64Binary(String name, Base64Binary value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			composeBytes("value", value.getValue());
//			close();
//		}
//	}
//
//	protected void composeId(String name, Id value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", value.getValue());
//			close();
//		}
//	}
//
//	protected void composeCode(String name, Code value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", value.getValue());
//			close();
//		}
//	}
//
//	protected void composeOid(String name, Oid value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", value.getValue());
//			close();
//		}
//	}
//
//	protected void composeUuid(String name, Uuid value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", value.getValue());
//			close();
//		}
//	}
//
//	protected void composeSid(String name, Sid value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", value.getValue().toString());
//			close();
//		}
//	}
//
//	protected void composeUriSimple(String name, Uri value) throws Exception {
//		if (value != null) {
//			prop(name, value.getValue().toString());
//		}
//	}
//
//	protected void composeUri(String name, Uri value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", value.getValue().toString());
//			close();
//		}
//	}
//
//	protected void composeUri(String name, java.net.URI value) throws Exception {
//		composeURI(name, value);
//	}
//
//
//	protected void composeDecimal(String name, Decimal value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", value.getValue().toString());
//			close();
//		}
//	}
//
//	protected void composeString(String name, String value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", value.getValue());
//			close();
//		}
//	}
//
//	protected void composeBoolean(String name, Boolean value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", java.lang.Boolean.toString(value.getValue()));
//			close();
//		}
//	}
//
//	protected void composeBooleanSimple(String name, Boolean value) throws Exception {
//		if (value != null) {
//			prop("value", java.lang.Boolean.toString(value.getValue()));
//		}
//	}
//
//	protected void composeBoolean(String name, java.lang.Boolean value) throws Exception {
//		if (value != null) {
//			open(name);
//			prop("value", value.toString());
//			close();
//		}
//	}
//
//	protected void composeInstant(String name, Instant value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", dateToXml(value.getValue()));
//			close();
//		}
//	}
//
//	protected void composeInteger(String name, Integer value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", java.lang.Integer.toString(value.getValue()));
//			close();
//		}
//	}
//
//	protected void composeDate(String name, java.util.Calendar value) throws Exception {
//		if (value != null) {
//			prop(name, dateToXml(value));
//		}
//	}
//
//	protected void composeDate(String name, Date value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", value.getValue());
//			close();
//		}
//	}
//
//	protected void composeDateSimple(String name, Date value) throws Exception {
//		if (value != null) {
//			prop(name, value.getValue());
//		}
//	}
//
//	protected void composeDateSimple(String name, Instant value) throws Exception {
//		if (value != null) {
//			composeDate(name, value.getValue());
//		}
//	}
//
//	protected void composeDateTime(String name, DateTime value) throws Exception {
//		if (value != null) {
//			open(name);
//			composeTypeAttributes(value);
//			prop("value", value.getValue());
//			close();
//		}
//	}
//
//	protected void composeDateTimeSimple(String name, DateTime value) throws Exception {
//		if (value != null) {
//			prop(name, value.getValue());
//		}
//	}

	protected void open(String name) throws Exception {
		if (name != null) 
			json.key(name);
		json.object();
	}

	protected void close() throws Exception {
		json.endObject();
	}

	protected void openArray(String name) throws Exception {
		if (name != null) 
			json.key(name);
		json.array();
	}

	protected void closeArray() throws Exception {
		json.endArray();
	}

  protected void composeBinary(String name, Binary element) throws Exception {
    if (element != null) {
      open(name);
      if (element.getXmlId() != null)
        prop("_id", element.getXmlId());
      prop("contentType", element.getContentType());
      prop("content", toString(element.getContent()));
      close();
    }     
  } 
}