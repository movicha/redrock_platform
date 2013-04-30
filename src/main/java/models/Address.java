package models;

import java.util.List;

import resources.EnumFactory;
import resources.Type;

public interface Address extends Type {
	public enum AddressUse {
		home, // A communication address at a home
		work, // An office address. First choice for business related contacts
				// during business hours
		temp, // A temporary address. The period can provide more detailed
				// information
		old, // This address is no longer in use (or was never correct, but
				// retained for records)
		Null; // added to help the parsers
		public static AddressUse fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("home".equals(codeString))
				return home;
			if ("work".equals(codeString))
				return work;
			if ("temp".equals(codeString))
				return temp;
			if ("old".equals(codeString))
				return old;
			throw new Exception("Unknown AddressUse code '" + codeString + "'");
		}

		public String toCode() {
			switch (this) {
			case home:
				return "home";
			case work:
				return "work";
			case temp:
				return "temp";
			case old:
				return "old";
			default:
				return "?";
			}
		}
	}

	public class AddressUseEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("home".equals(codeString))
				return AddressUse.home;
			if ("work".equals(codeString))
				return AddressUse.work;
			if ("temp".equals(codeString))
				return AddressUse.temp;
			if ("old".equals(codeString))
				return AddressUse.old;
			throw new Exception("Unknown AddressUse code '" + codeString + "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == AddressUse.home)
				return "home";
			if (code == AddressUse.work)
				return "work";
			if (code == AddressUse.temp)
				return "temp";
			if (code == AddressUse.old)
				return "old";
			return "?";
		}
	}

	public Enum<AddressUse> getUse();

	public void setUse(Enum<AddressUse> value);

	public AddressUse getUseSimple();

	public void setUseSimple(AddressUse value);

	public String getText();

	public void setText(String value);

	public String getTextSimple();

	public void setTextSimple(String value);

	public List<String> getLine();

	public String getCity();

	public void setCity(String value);

	public String getCitySimple();

	public void setCitySimple(String value);

	public String getState();

	public void setState(String value);

	public String getStateSimple();

	public void setStateSimple(String value);

	public String getZip();

	public void setZip(String value);

	public String getZipSimple();

	public void setZipSimple(String value);

	public String getCountry();

	public void setCountry(String value);

	public String getCountrySimple();

	public void setCountrySimple(String value);

	public Period getPeriod();

	public void setPeriod(Period value);
}
