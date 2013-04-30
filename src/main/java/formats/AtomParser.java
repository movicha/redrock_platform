package formats;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import models.AtomEntry;
import models.AtomEntryImpl;
import models.AtomFeed;
import models.AtomFeedImpl;
import models.Resource;
import resources.ResourceReference;
import utilities.xhtml.XhtmlParser;
import utilities.json.JsonArray;
import utilities.json.JsonObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AtomParser extends XmlBase {
  
  public AtomFeed parse(InputStream input) throws Exception {
    XmlPullParser xpp = loadXml(input);
  
    if (!xpp.getNamespace().equals(ATOM_NS))
      throw new Exception("This does not appear to be an atom feed (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
    return parseAtom(xpp);
  }
  
  public AtomFeed parse(XmlPullParser xpp) throws Exception {
    if (!xpp.getNamespace().equals(ATOM_NS))
      throw new Exception("This does not appear to be an atom feed (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
    return parseAtom(xpp);
  }

  public AtomFeed parse(JsonObject json) throws Exception {
    return parseAtom(json);
  }

  private String parseString(XmlPullParser xpp) throws Exception {
    String res = null;
    if (xpp.next() == XmlPullParser.TEXT) {
      res = xpp.getText();
      if (xpp.next() != XmlPullParser.END_TAG)
        throw new Exception("Bad String Structure");
    }
    xpp.next();
    return res;
  }
  
  private AtomFeed parseAtom(XmlPullParser xpp) throws Exception {
    AtomFeed res = new AtomFeedImpl();
    if (!xpp.getName().equals("feed"))
      throw new Exception("This does not appear to be an atom feed (wrong name '"+xpp.getName()+"') (@ /)");
    xpp.next();
    
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitle(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("id"))
        res.setId(parseString(xpp));
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")){
        res.getLinks().put(xpp.getAttributeValue(null, "rel"), xpp.getAttributeValue(null, "href"));
        skipEmptyElement(xpp);
      } else if(eventType == XmlPullParser.START_TAG && xpp.getName().equals("updated"))
        res.setUpdated(parseDate(xpp));
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("entry"))
        res.getEntryList().add(parseEntry(xpp));
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        xpp.next();
        eventType = nextNoWhitespace(xpp);
        while (eventType != XmlPullParser.END_TAG) {
          if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
            res.setAuthorName(parseString(xpp));
          } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uri"))
            res.setAuthorUri(parseString(xpp));
          else
            throw new Exception("Bad Xml parsing entry.author");
          eventType = nextNoWhitespace(xpp);
        }
        xpp.next();
      }
      else
        throw new Exception("Bad Xml parsing Atom Feed");
      eventType = nextNoWhitespace(xpp);
    }

    return res;  
  }

  private AtomFeed parseAtom(JsonObject json) throws Exception {
    AtomFeed res = new AtomFeedImpl();
    if (json.has("title"))
      res.setTitle(json.getString("title"));
    if (json.has("id"))
      res.setId(json.getString("id"));
    if (json.has("updated"))
      res.setUpdated(xmlToDate(json.getString("updated")));
    if (json.has("authors")) {
      JsonObject author = json.getJsonArray("authors").getJsonObject(0);
      if (author.has("name"))
        res.setAuthorName(author.getString("name"));
      if (author.has("uri"))
        res.setAuthorUri(author.getString("uri"));
    }
    if (json.has("links")) {
      JsonArray array = json.getJsonArray("links");
      for (int i = 0; i < array.length(); i++) {
        parseLink(res.getLinks(), array.getJsonObject(i));
      }
    }
    JsonArray array = json.getJsonArray("entries");
    for (int i = 0; i < array.length(); i++) {
      res.getEntryList().add(parseEntry(array.getJsonObject(i)));
    }
    return res;  
  }

  private void parseLink(Map<String, String> links, JsonObject json) throws Exception {
    if (json.has("href") && json.has("rel"))
    links.put(json.getString("rel"), json.getString("href"));    
  }

  private AtomEntry parseEntry(JsonObject json) throws Exception {
    AtomEntry res = new AtomEntryImpl();
    if (json.has("title"))
      res.setTitle(json.getString("title"));
    if (json.has("id"))
      res.setId(json.getString("id"));
    if (json.has("updated"))
      res.setUpdated(xmlToDate(json.getString("updated")));
    if (json.has("published"))
      res.setPublished(xmlToDate(json.getString("published")));
    if (json.has("links")) {
      JsonArray array = json.getJsonArray("links");
      for (int i = 0; i < array.length(); i++) {
        parseLink(res.getLinks(), array.getJsonObject(i));
      }
    }
    if (json.has("authors")) {
      JsonObject author = json.getJsonArray("authors").getJsonObject(0);
      if (author.has("name"))
        res.setAuthorName(author.getString("name"));
      if (author.has("uri"))
        res.setAuthorUri(author.getString("uri"));
    }
    if (json.has("categories")) {
      JsonObject cat = json.getJsonArray("categories").getJsonObject(0);
      if (cat.has("term"))
        res.setCategory(cat.getString("term"));
    }
    if (json.has("summary"))
      res.setSummary(new XhtmlParser().parse(json.getString("summary"), "div").getChildNodes().get(0));
    if (json.has("content"))
      res.setResource(new JsonParser().parse(json.getJsonObject("content")));
    return res;
  }
  
  private AtomEntry parseEntry(XmlPullParser xpp) throws Exception {
    AtomEntry res = new AtomEntryImpl();
    
    xpp.next();    
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitle(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("id"))
        res.setId(parseString(xpp));
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")) {
        res.getLinks().put(xpp.getAttributeValue(null, "rel"), xpp.getAttributeValue(null, "href"));
        skipEmptyElement(xpp);
      } else if(eventType == XmlPullParser.START_TAG && xpp.getName().equals("updated"))
        res.setUpdated(parseDate(xpp));
      else if(eventType == XmlPullParser.START_TAG && xpp.getName().equals("published"))
        res.setPublished(parseDate(xpp));
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        if ("http://hl7.org/fhir/resource-types".equals(xpp.getAttributeValue(null, "scheme"))) 
         res.setCategory(xpp.getAttributeValue(null, "term"));
         skipEmptyElement(xpp);
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        xpp.next();
        eventType = nextNoWhitespace(xpp);
        while (eventType != XmlPullParser.END_TAG) {
          if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
            res.setAuthorName(parseString(xpp));
          } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uri"))
            res.setAuthorUri(parseString(xpp));
          else
            throw new Exception("Bad Xml parsing entry.author");
          eventType = nextNoWhitespace(xpp);
        }
        xpp.next();
      }
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        xpp.next();
        nextNoWhitespace(xpp);
        XmlParser p = new XmlParser();
        res.setResource(p.parse(xpp));
        xpp.next();
        nextNoWhitespace(xpp);
        xpp.next();
        
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("summary")) {
        xpp.next();
        nextNoWhitespace(xpp);
        res.setSummary(new XhtmlParser().parseHtmlNode(xpp));
        xpp.next();
        nextNoWhitespace(xpp);
        xpp.next();
      } else
        throw new Exception("Bad Xml parsing entry");
      eventType = nextNoWhitespace(xpp);
    }

    xpp.next();
    return res;  
  }

  private Calendar parseDate(XmlPullParser xpp) throws Exception {
    return xmlToDate(parseString(xpp));    
  }
}