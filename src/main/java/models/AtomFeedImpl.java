package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AtomFeedImpl implements AtomFeed {
  private String id;
  private String title;
  private Map<String, String> links = new LinkedHashMap<String, String>();
  private java.util.Calendar updated;
  private String authorName;
  private String authorUri;

  private List<AtomEntry> entryList = new ArrayList<AtomEntry>();

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

  public java.util.Calendar getUpdated() {
    return updated;
  }

  public void setUpdated(java.util.Calendar updated) {
    this.updated = updated;
  }

  public List<AtomEntry> getEntryList() {
    return entryList;
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

  public Map<String, String> getLinks() {
    return links;
  }
  
  
}

