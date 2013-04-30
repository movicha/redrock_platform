package formats;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import models.*;
import utilities.Binary;
import utilities.BinaryImpl;
import utilities.xhtml.XhtmlNode;
import utilities.xhtml.XhtmlParser;
import org.xmlpull.v1.XmlPullParser;

import resources.Element;
import resources.Type;

public abstract class XmlParserBase extends ParserBase {
	private boolean allowUnknownContent;
	public boolean isAllowUnknownContent() {
		return allowUnknownContent;
	}
	
	public void setAllowUnknownContent(boolean allowUnknownContent) {
		this.allowUnknownContent = allowUnknownContent;
	}

	abstract protected Resource parseResource(XmlPullParser xpp) throws Exception;

	/** -- worker routines --------------------------------------------------- */

	protected void parseTypeAttributes(XmlPullParser xpp, Type t) throws Exception {
		parseElementAttributes(xpp, t);
	}

	protected void parseElementAttributes(XmlPullParser xpp, Element e) throws Exception {
		if (xpp.getAttributeValue(null, "id") != null) {
			e.setXmlId(xpp.getAttributeValue(null, "id"));
			idMap.put(e.getXmlId(), e);
		}
	}

	protected void parseResourceAttributes(XmlPullParser xpp, Resource r) throws Exception {
		parseElementAttributes(xpp, r);
	}


	private String pathForLocation(XmlPullParser xpp) {
		return xpp.getPositionDescription();
	}


	public ResourceOrFeed parseGeneral(InputStream input) throws Exception {
		XmlPullParser xpp = loadXml(input);
		ResourceOrFeed r = new ResourceOrFeed();

		if (xpp.getNamespace().equals(FHIR_NS))
			r.resource = parseResource(xpp);
		else if (xpp.getNamespace().equals(ATOM_NS)) 
			r.feed = new AtomParser().parse(xpp);
		else
			throw new Exception("This does not appear to be a FHIR resource (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
		return r;    
	}

	public Resource parse(InputStream input) throws Exception {
		XmlPullParser xpp = loadXml(input);

		if (!xpp.getNamespace().equals(FHIR_NS))
			throw new Exception("This does not appear to be a FHIR resource (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
		return parseResource(xpp);
	}

	public Resource parse(XmlPullParser xpp) throws Exception {
		if (!xpp.getNamespace().equals(FHIR_NS))
			throw new Exception("This does not appear to be a FHIR resource (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
		return parseResource(xpp);
	}


	protected void unknownContent(XmlPullParser xpp) throws Exception {
		if (!isAllowUnknownContent())
			throw new Exception("Unknown Content "+xpp.getName()+" @ "+pathForLocation(xpp));
	}

	protected XhtmlNode parseXhtml(XmlPullParser xpp) throws Exception {
		XhtmlParser prsr = new XhtmlParser();
		return prsr.parseHtmlNode(xpp);
	}

	protected Resource parseBinary(XmlPullParser xpp) throws Exception {
		Binary res = new BinaryImpl();
		parseElementAttributes(xpp, res);
		res.setContentType(xpp.getAttributeValue(null, "contentType"));
		int eventType = xpp.next();
		if (eventType == XmlPullParser.TEXT) {
			res.setContent(Base64.decodeBase64(xpp.getText().getBytes()));
			eventType = xpp.next();
		}
		if (eventType != XmlPullParser.END_TAG)
			throw new Exception("Bad String Structure");
		xpp.next();
		return res;
	}  
}