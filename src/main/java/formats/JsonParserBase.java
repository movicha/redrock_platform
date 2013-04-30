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
import utilities.TextFile;
import utilities.Utilities;
import utilities.xhtml.XhtmlNode;
import utilities.xhtml.XhtmlParser;
import utilities.json.JsonObject;
import org.xmlpull.v1.XmlPullParser;

import resources.Element;

public abstract class JsonParserBase extends ParserBase {
    abstract protected Resource parseResource(JsonObject json) throws Exception;

    private JsonObject loadJson(InputStream input) throws Exception { 
        return new JsonObject(TextFile.streamToString(input));
    }
  
    public ResourceOrFeed parseGeneral(InputStream input) throws Exception {
        JsonObject json = loadJson(input);
        ResourceOrFeed r = new ResourceOrFeed();
    
        if (json.has("entries"))
      		r.feed = new AtomParser().parse(json);
        else  
      		r.resource = parseResource(json);
  		return r;    
	}

	public Resource parse(InputStream input) throws Exception {
	  JsonObject json = loadJson(input);

	  return parseResource(json);
	}

	public Resource parse(JsonObject json) throws Exception {
	  return parseResource(json);
	}

	protected void parseElementProperties(JsonObject json, Element e) throws Exception {
	  if (json.has("_id"))
	    e.setXmlId(json.getString("_id"));
	  if (!Utilities.noString(e.getXmlId()))
	    idMap.put(e.getXmlId(), e);
	}

	protected abstract void parseResourceProperties(JsonObject json, Resource r) throws Exception;

	protected XhtmlNode parseXhtml(String value) throws Exception {
	  XhtmlParser prsr = new XhtmlParser();
	  return prsr.parse(value, "div").getChildNodes().get(0);
	}

	protected Resource parseBinary(JsonObject json) throws Exception {
	  Binary res = new BinaryImpl();
	  parseResourceProperties(json, res);
	  res.setContentType(json.getString("contentType"));
	  res.setContent(Base64.decodeBase64(json.getString("content").getBytes()));
	  return res;
	}  
}
