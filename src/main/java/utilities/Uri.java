package utilities;

import java.net.URI;
import java.util.List;

import resources.Extension;
import resources.Type;

public interface Uri extends Type {
	public URI getValue();
	public void setValue(URI value);
}
