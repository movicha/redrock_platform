package utilities;

import java.io.ByteArrayInputStream;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

public class ZipUriResolver implements URIResolver {

	private Map<String, byte[]> files;

	public ZipUriResolver(Map<String, byte[]> files) {
		super();
		this.files = files;
	}

	@Override
	public Source resolve(String arg0, String arg1) throws TransformerException {
		try {
			byte[] bs = files.get(arg0);
			return new StreamSource(new ByteArrayInputStream(bs));
		} catch (Exception e) {
			throw new TransformerException(e);
		}
	}

}