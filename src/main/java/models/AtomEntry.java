package models;

import java.util.Map;

import utilities.xhtml.XhtmlNode;

public interface AtomEntry {
	  public String getId();
	  public void setId(String id);
	  public String getTitle();
	  public void setTitle(String title);
	  public Map<String, String> getLinks();
	  public String getCategory();
	  public void setCategory(String category);
	  public String getAuthorName();
	  public void setAuthorName(String authorName);
	  public String getAuthorUri();
	  public void setAuthorUri(String authorUri);
	  public java.util.Calendar getPublished();
	  public void setPublished(java.util.Calendar published);
	  public java.util.Calendar getUpdated();
	  public void setUpdated(java.util.Calendar updated);
	  public Resource getResource();
	  public void setResource(Resource resource);
	  public XhtmlNode getSummary();
	  public void setSummary(XhtmlNode summary);
}
