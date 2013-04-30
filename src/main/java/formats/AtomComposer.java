package formats;

import java.io.OutputStream;

import models.AtomEntry;
import models.AtomFeed;
import utilities.xhtml.XhtmlComposer;
import utilities.xml.XmlWriter;
import utilities.xml.XmlWriterImpl;

public class AtomComposer extends XmlBase {
  private XmlWriter xml;
  private boolean htmlPretty;

  public void compose(OutputStream stream, AtomFeed feed, boolean pretty) throws Exception {
    XmlWriterImpl writer = new XmlWriterImpl(stream, "UTF-8");
    writer.setPretty(pretty);
    writer.start();
    compose(writer, feed, pretty);
    writer.close();
  }
  
  public void compose(OutputStream stream, AtomFeed feed, boolean pretty, boolean htmlPretty) throws Exception {
    XmlWriterImpl writer = new XmlWriterImpl(stream, "UTF-8");
    writer.setPretty(pretty);
    writer.start();
    compose(writer, feed, htmlPretty);
    writer.close();
  }
  
  public void compose(XmlWriter writer, AtomFeed feed, boolean htmlPretty) throws Exception {
    this.htmlPretty = htmlPretty;
    xml = writer;
    xml.setDefaultNamespace(ATOM_NS);
    composeFeed(feed);
  }

  // standard order for round-tripping examples succesfully:
  // title, id, links, updated, published, authors 
  private void composeFeed(AtomFeed feed) throws Exception {
    xml.open(ATOM_NS, "feed");
    if (feed.getTitle() != null)
      xml.element(ATOM_NS, "title", feed.getTitle());
    if (feed.getId() != null)
      xml.element(ATOM_NS, "id", feed.getId());
    for (String n : feed.getLinks().keySet()) {
      xml.attribute("href", feed.getLinks().get(n));
      xml.attribute("rel", n);
      xml.element(ATOM_NS, "link", null);
    }
    if (feed.getUpdated() != null)
      xml.element(ATOM_NS, "updated", dateToXml(feed.getUpdated()));
    if (feed.getAuthorName() != null || feed.getAuthorUri() != null) {
      xml.open(ATOM_NS, "author");
      if (feed.getAuthorName() != null)
        xml.element(ATOM_NS, "name", feed.getAuthorName());
      if (feed.getAuthorUri() != null)
        xml.element(ATOM_NS, "uri", feed.getAuthorUri());
      xml.close(ATOM_NS, "author");
    }
    for (AtomEntry e : feed.getEntryList())
      composeEntry(e);
    xml.close(ATOM_NS, "feed");
  }

  // standard order for round-tripping examples succesfully:
  // title, id, links, updated, published, authors 
  private void composeEntry(AtomEntry e) throws Exception  {
    
    xml.open(ATOM_NS, "entry");
    if (e.getTitle() != null)
      xml.element(ATOM_NS, "title", e.getTitle());
    if (e.getId() != null)
      xml.element(ATOM_NS, "id", e.getId());
    for (String n : e.getLinks().keySet()) {
      xml.attribute("href", e.getLinks().get(n));
      xml.attribute("rel", n);
      xml.element(ATOM_NS, "link", null);
    }
    if (e.getUpdated() != null)
      xml.element(ATOM_NS, "updated", dateToXml(e.getUpdated()));
    if (e.getPublished() != null)
      xml.element(ATOM_NS, "published", dateToXml(e.getPublished()));
    if (e.getAuthorName() != null || e.getAuthorUri() != null) {
      xml.open(ATOM_NS, "author");
      if (e.getAuthorName() != null)
        xml.element(ATOM_NS, "name", e.getAuthorName());
      if (e.getAuthorUri() != null)
        xml.element(ATOM_NS, "uri", e.getAuthorUri());
      xml.close(ATOM_NS, "author");
    }
    if (e.getCategory() != null) {      
      xml.attribute("scheme", "http://hl7.org/fhir/resource-types");
      xml.attribute("term", e.getCategory());
      xml.element(ATOM_NS, "category", null);
    }

    xml.attribute("type", "text/xml");
    xml.open(ATOM_NS, "content");
    new XmlComposer().compose(xml, e.getResource(), htmlPretty);
    xml.close(ATOM_NS, "content");
    
    if (e.getSummary() != null) {
      xml.attribute("type", "xhtml");
      xml.open(ATOM_NS, "summary");
      xml.namespace(XhtmlComposer.XHTML_NS, null);
      boolean oldPretty = xml.isPretty();
      xml.setPretty(htmlPretty);
      new XhtmlComposer().compose(xml, e.getSummary());
      xml.setPretty(oldPretty);
      xml.close(ATOM_NS, "summary");
    }
    xml.close(ATOM_NS, "entry");
  }
}