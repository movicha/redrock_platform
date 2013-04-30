package formats;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.codec.binary.Base64;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlBase {
	protected static final String FHIR_NS = "http://hl7.org/fhir";
	protected static final String ATOM_NS = "http://www.w3.org/2005/Atom";
	protected static final String GDATA_NS = "http://schemas.google.com/g/2005";

	protected XmlPullParser loadXml(InputStream stream) throws Exception {
		BufferedInputStream input = new BufferedInputStream(stream);
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance(
			System.getProperty(XmlPullParserFactory.PROPERTY_NAME), null);
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		xpp.setInput(input, "UTF-8");
		xpp.next();
		return xpp;
	}

	protected int nextNoWhitespace(XmlPullParser xpp) throws Exception {
		int eventType = xpp.getEventType();
		while (eventType == XmlPullParser.TEXT && xpp.isWhitespace())
			eventType = xpp.next();
		return eventType;
	}

	protected String dateToXml(java.util.Calendar date) {
		// there's a better way to do this in java 1.7, but for now going java 1.7 is too hard for implementers
		// String res = new SimpleDateFormat(XML_DATE_PATTERN).format(date);
		// return res.substring(0, 22)+":"+res.substring(22);
		return javax.xml.bind.DatatypeConverter.printDateTime(date);
	}

	protected java.util.Calendar xmlToDate(String date) throws ParseException {
		// there's a better way to do this in java 1.7, but for now going java 1.7 is too hard for implementers
		// if (date.length() > 23)
		//     date = date.substring(0, 22)+date.substring(23);  
		// return new SimpleDateFormat(XML_DATE_PATTERN).parse(date);
		return javax.xml.bind.DatatypeConverter.parseDateTime(date);
	}

	protected void skipEmptyElement(XmlPullParser xpp) throws Exception {
		while (xpp.getEventType() != XmlPullParser.END_TAG) 
			xpp.next();
		xpp.next();
	}

	protected String toString(String value) {
		return value;
	}

	protected String toString(int value) {
		return java.lang.Integer.toString(value);
	}

	protected String toString(boolean value) {
		return java.lang.Boolean.toString(value);
	}

	protected String toString(BigDecimal value) {
		return value.toString();
	}

	protected String toString(URI value) {
		return value.toString();
	}

	protected String toString(byte[] value) {
		byte[] encodeBase64 = Base64.encodeBase64(value);
		return new String(encodeBase64);
	}

	protected String toString(Calendar value) {
	    return dateToXml(value);
	}
}