package models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import utilities.xhtml.XhtmlNode;

public class AtomEntryImpl implements AtomEntry {
  private String id;
  private String title;
  private Map<String, String> links = new LinkedHashMap<String, String>();
  private String category;
  private String authorName;
  private String authorUri;
  private java.util.Calendar published;
  private java.util.Calendar updated;
  private Resource resource;
  private XhtmlNode summary;
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }

  public Map<String, String> getLinks() {
    return links;
  }
  
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
  
  public String getAuthorName() {
    return authorName;
  }
  
  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }
  
  public String getAuthorUri() {
    return authorUri;
  }
  
  public void setAuthorUri(String authorUri) {
    this.authorUri = authorUri;
  }
  
  public java.util.Calendar getPublished() {
    return published;
  }
  
  public void setPublished(java.util.Calendar published) {
    this.published = published;
  }
  
  public java.util.Calendar getUpdated() {
    return updated;
  }
  
  public void setUpdated(java.util.Calendar updated) {
    this.updated = updated;
  }
  
  public Resource getResource() {
    return resource;
  }
  
  public void setResource(Resource resource) {
    this.resource = resource;
  }
  
  public XhtmlNode getSummary() {
    return summary;
  }
  
  public void setSummary(XhtmlNode summary) {
    this.summary = summary;
  }
}  
