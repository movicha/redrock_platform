package utilities;

import java.util.Iterator;
import java.util.List;

import models.Narrative;
import models.Resource;
import resources.Code;
import resources.Extension;
import resources.ResourceType;

public class BinaryImpl implements Binary {
	// @Override
	public ResourceType getResourceType() {
		return ResourceType.Binary;
	}

	private byte[] content;
	private String contentType;

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public Narrative getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(Narrative text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Code getLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLanguage(Code value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLanguageSimple() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLanguageSimple(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Resource> getContained() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getXmlId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setXmlId(String xmlId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Extension> getExtensions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasExtensions() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}