package formats;

import java.io.OutputStream;
import java.math.BigDecimal;
import org.apache.commons.codec.binary.Base64;

import resources.Code;
import resources.DateTime;
import resources.Element;
import resources.Type;
import models.Id;
import models.Instant;
import models.Resource;
import utilities.Base64Binary;
import utilities.Binary;
import utilities.Uri;
import utilities.xhtml.XhtmlComposer;
import utilities.xhtml.XhtmlNode;
import utilities.xml.XmlWriter;
import utilities.xml.XmlWriterImpl;

public abstract class XmlComposerBase extends XmlBase {
	protected XmlWriter xml;
	protected boolean htmlPretty;

	public void compose(OutputStream stream, Resource resource, boolean pretty) throws Exception {
		XmlWriterImpl writer = new XmlWriterImpl(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, resource, pretty);
		writer.close();
	}

	public void compose(OutputStream stream, Resource resource, boolean pretty, boolean htmlPretty) throws Exception {
		XmlWriterImpl writer = new XmlWriterImpl(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, resource, htmlPretty);
		writer.close();
	}

	public void compose(XmlWriter writer, Resource resource, boolean htmlPretty) throws Exception {
		this.htmlPretty = htmlPretty;
		xml = writer;
		xml.setDefaultNamespace(FHIR_NS);
		composeResource(resource);
	}

	protected abstract void composeResource(Resource resource) throws Exception;

	protected void composeElementAttributes(Element element) throws Exception {
		if (element.getXmlId() != null) 
			xml.attribute("id", element.getXmlId());
	}

	protected void composeElementAttributes(Enum<?> element) throws Exception {
		if (element != null) 
			xml.attribute("id", toString(element.hashCode()));
	}
	
	protected void composeTypeAttributes(Type type) throws Exception {
		composeElementAttributes(type);
	}
  
	protected void composeString(String name, String value) throws Exception {
	    if (value != null)
	        xml.element(FHIR_NS, name, value);
	}

	protected void composeURI(String name, java.net.URI value) throws Exception {
	    if (value != null)
	        xml.element(FHIR_NS, name, value.toString());
	}
	
	protected void composeUri(String name, java.net.URI value) throws Exception {
	    if (value != null) {
	        xml.element(FHIR_NS, name, value.toString());
	    }
	} 
	// 
	protected void composeBigDecimal(String name, BigDecimal value) throws Exception {
	    if (value != null)
	        xml.element(FHIR_NS, name, value.toString());
	}
	
	protected void composeDecimal(String name, BigDecimal value) throws Exception {
	    if (value != null) {
	         xml.element(FHIR_NS, name, value.toString());
	    }
	}
	
	protected void composeInt(String name, java.lang.Integer value) throws Exception {
	    if (value != null)
	        xml.element(FHIR_NS, name, value.toString());
	}
	
	protected void composeInteger(String name, java.lang.Integer value) throws Exception {
	    if (value != null) {
	        xml.element(FHIR_NS, name, java.lang.Integer.toString(value));
	    }
	}
	
	protected void composeBool(String name, java.lang.Boolean value) throws Exception {
	    if (value != null)
	        xml.element(FHIR_NS, name, value.toString());
	}
	

	protected void composeXhtml(String name, XhtmlNode html) throws Exception {
		XhtmlComposer comp = new XhtmlComposer();
		// name is also found in the html and should the same
		// ? check that
		boolean oldPretty = xml.isPretty();
		xml.setPretty(htmlPretty);
		xml.namespace(XhtmlComposer.XHTML_NS, null);
		comp.compose(xml, html);
		xml.setPretty(oldPretty);
	}

	protected void composeBytes(String name, byte[] content) throws Exception {
	    if (content != null) {
	        byte[] encodeBase64 = Base64.encodeBase64(content);
	        composeString(name, new String(encodeBase64));
	    }
	}  
	
	protected void composeBase64Binary(String name, Base64Binary value) throws Exception {
	    if (value != null) {
	        composeTypeAttributes(value);
	        composeBytes(name, value.getValue());
	    }
	}
	
	protected void composeBase64Binary(String name, byte[] value) throws Exception {
	        composeBytes(name, value);
	}
	
	
	protected void composeId(String name, Id value) throws Exception {
	    if (value != null) {
	        composeTypeAttributes(value);
	        xml.element(FHIR_NS, name, value.getValue());
	    }
	}
	
	protected void composeId(String name, String value) throws Exception {
	    if (value != null) {
	        xml.element(FHIR_NS, name, value);
	    }
	}
	
	protected void composeCode(String name, Code value) throws Exception {
	    if (value != null) {
	        composeTypeAttributes(value);
	        xml.element(FHIR_NS, name, value.getCodeText());
	    }
	}
	
	protected void composeCode(String name, String value) throws Exception {
	    if (value != null) {
	        xml.element(FHIR_NS, name, value);
	    }
	}
		
	protected void composeUri(String name, Uri value) throws Exception {
	    if (value != null) {
	        composeTypeAttributes(value);
	        xml.element(FHIR_NS, name, value.getValue().toString());
	    }
	}
	
	protected void composeBoolean(String name, Boolean value) throws Exception {
	    if (value != null) {
	        xml.element(FHIR_NS, name, value.toString());
	    }
	}
	  
	protected void composeInstant(String name, Instant value) throws Exception {
	    if (value != null) {
	        composeTypeAttributes(value);
	        xml.element(FHIR_NS, name, dateToXml(value.getValue()));
	    }
	}
		
	protected void composeDate(String name, java.util.Calendar value) throws Exception {
	    if (value != null) {
	        xml.element(FHIR_NS, name, dateToXml(value));
	    }
	}
		
	protected void composeDateTime(String name, DateTime value) throws Exception {
	    if (value != null) {
	        composeTypeAttributes(value);
	        xml.element(FHIR_NS, name, value.getValue());
	    }
	}
	
	protected void composeDateTime(String name, String value) throws Exception {
	    if (value != null) {
	        xml.element(FHIR_NS, name, value);
	    }
	}
	
	// protected void composeString(String name, String value) throws Exception {
	//    if (value != null) {
	//        xml.element(FHIR_NS, name, value);
	//    }
	// }
	
	protected void composeBinary(String name, Binary element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.attribute("contentType", element.getContentType());
			xml.open(FHIR_NS, name);
			xml.text(toString(element.getContent()));
			xml.close(FHIR_NS, name);
		}      
	}
}
