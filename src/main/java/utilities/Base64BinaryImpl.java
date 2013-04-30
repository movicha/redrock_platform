package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import resources.Extension;

public class Base64BinaryImpl implements Base64Binary {
	private ArrayList<Byte> bytes;

	private byte[] value;

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
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