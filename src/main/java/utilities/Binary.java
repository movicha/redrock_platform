package utilities;

import models.Resource;
import resources.ResourceType;

public interface Binary extends Resource {
	// @Override
	public ResourceType getResourceType();

	public byte[] getContent();

	public void setContent(byte[] content);

	public String getContentType();

	public void setContentType(String contentType);
}