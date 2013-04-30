package utilities;

import resources.Type;

public interface Base64Binary extends Type {
	public byte[] getValue();

	public void setValue(byte[] value);
}