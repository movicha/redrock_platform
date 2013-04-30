package models;

import java.util.List;
import java.util.Map;

public interface AtomFeed {
	  public String getId();
	  public void setId(String id);
	  public String getTitle();
	  public void setTitle(String title);
	  public java.util.Calendar getUpdated();
	  public void setUpdated(java.util.Calendar updated);
	  public List<AtomEntry> getEntryList();
	  public String getAuthorName();
	  public void setAuthorName(String authorName);
	  public String getAuthorUri();
	  public void setAuthorUri(String authorUri);
	  public Map<String, String> getLinks();
}
