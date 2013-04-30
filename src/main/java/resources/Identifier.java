package resources;

import java.net.URI;

import models.Period;
import utilities.Uri;
import utilities.UriImpl;

public interface Identifier extends Type {
	public enum IdentifierUse {
		usual, // the identifier recommended for display and use in real-world
				// interactions
		official, // the identifier considered to be most trusted for the
					// identification of this item
		temp, // A temporary identifier
		Null; // added to help the parsers
		public static IdentifierUse fromCode(String codeString)
				throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("usual".equals(codeString))
				return usual;
			if ("official".equals(codeString))
				return official;
			if ("temp".equals(codeString))
				return temp;
			throw new Exception("Unknown IdentifierUse code '" + codeString
					+ "'");
		}

		public String toCode() {
			switch (this) {
			case usual:
				return "usual";
			case official:
				return "official";
			case temp:
				return "temp";
			default:
				return "?";
			}
		}
	}

	public class IdentifierUseEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("usual".equals(codeString))
				return IdentifierUse.usual;
			if ("official".equals(codeString))
				return IdentifierUse.official;
			if ("temp".equals(codeString))
				return IdentifierUse.temp;
			throw new Exception("Unknown IdentifierUse code '" + codeString
					+ "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == IdentifierUse.usual)
				return "usual";
			if (code == IdentifierUse.official)
				return "official";
			if (code == IdentifierUse.temp)
				return "temp";
			return "?";
		}
	}

	public Uri getSystem();
	public void setSystem(Uri system);
	public Enum<IdentifierUse> getUse();
	public void setUse(Enum<IdentifierUse> enum1);
	public IdentifierUse getUseSimple();
	public void setUseSimple(IdentifierUse value);
	public String getLabel();
	public void setLabel(String value);
	public void setLabelSimple(String value);
	public URI getSystemSimple();
	public void setSystemSimple(URI value);
	public String getId();
	public void setId(String value);
	public String getIdSimple();
	public void setIdSimple(String value);
	public Period getPeriod();
	public void setPeriod(Period value);
	public ResourceReference getAssigner();
	public void setAssigner(ResourceReference value);
}
