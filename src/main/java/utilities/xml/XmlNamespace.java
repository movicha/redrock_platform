package utilities.xml;

public class XmlNamespace {

	private String namespace;
	private String abbreviation;
	
	public XmlNamespace(String namespace, String abbreviation) {
		super();
		setNamespace(namespace);
		setAbbreviation(abbreviation);
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

}
