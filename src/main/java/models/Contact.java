package models;

import resources.EnumFactory;
import resources.Type;

// Uses MySQL as backing data store
// Needs play.db.jpa.Model implementation
public interface Contact extends Type {
	public enum ContactSystem {
		phone, // The value is a telephone number used for voice calls. Use of
				// full international numbers starting with + is recommended to
				// enable automatic dialing support but not required.
		fax, // The value is a fax machine. Use of full international numbers
				// starting with + is recommended to enable automatic dialing
				// support but not required.
		email, // The value is an email address
		url, // The value is a url. This is intended for various personal
				// contacts including blogs, Twitter, Facebook, etc. Do not use
				// for email addresses
		Null; // added to help the parsers
		public static ContactSystem fromCode(String codeString)
				throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("phone".equals(codeString))
				return phone;
			if ("fax".equals(codeString))
				return fax;
			if ("email".equals(codeString))
				return email;
			if ("url".equals(codeString))
				return url;
			throw new Exception("Unknown ContactSystem code '" + codeString
					+ "'");
		}

		public String toCode() {
			switch (this) {
			case phone:
				return "phone";
			case fax:
				return "fax";
			case email:
				return "email";
			case url:
				return "url";
			default:
				return "?";
			}
		}
	}

	public class ContactSystemEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("phone".equals(codeString))
				return ContactSystem.phone;
			if ("fax".equals(codeString))
				return ContactSystem.fax;
			if ("email".equals(codeString))
				return ContactSystem.email;
			if ("url".equals(codeString))
				return ContactSystem.url;
			throw new Exception("Unknown ContactSystem code '" + codeString
					+ "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == ContactSystem.phone)
				return "phone";
			if (code == ContactSystem.fax)
				return "fax";
			if (code == ContactSystem.email)
				return "email";
			if (code == ContactSystem.url)
				return "url";
			return "?";
		}
	}

	public enum ContactUse {
		home, // A communication contact at a home; attempted contacts for
				// business purposes might intrude privacy and chances are one
				// will contact family or other household members instead of the
				// person one wishes to call. Typically used with urgent cases,
				// or if no other contacts are available.
		work, // An office contact. First choice for business related contacts
				// during business hours.
		temp, // A temporary contact. The period can provide more detailed
				// information.
		old, // This contact is no longer in use (or was never correct, but
				// retained for records)
		mobile, // A telecommunication device that moves and stays with its
				// owner. May have characteristics of all other use codes,
				// suitable for urgent matters, not the first choice for routine
				// business
		Null; // added to help the parsers
		public static ContactUse fromCode(String codeString) throws Exception {
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
			if ("mobile".equals(codeString))
				return mobile;
			throw new Exception("Unknown ContactUse code '" + codeString + "'");
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
			case mobile:
				return "mobile";
			default:
				return "?";
			}
		}
	}

	public class ContactUseEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("home".equals(codeString))
				return ContactUse.home;
			if ("work".equals(codeString))
				return ContactUse.work;
			if ("temp".equals(codeString))
				return ContactUse.temp;
			if ("old".equals(codeString))
				return ContactUse.old;
			if ("mobile".equals(codeString))
				return ContactUse.mobile;
			throw new Exception("Unknown ContactUse code '" + codeString + "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == ContactUse.home)
				return "home";
			if (code == ContactUse.work)
				return "work";
			if (code == ContactUse.temp)
				return "temp";
			if (code == ContactUse.old)
				return "old";
			if (code == ContactUse.mobile)
				return "mobile";
			return "?";
		}
	}

	public Enum<ContactSystem> getSystem();
	public void setSystem(Enum<ContactSystem> value);
	public ContactSystem getSystemSimple();
	public void setSystemSimple(ContactSystem value);
	public String getValue();
	public void setValue(String value);
	public String getValueSimple();
	public void setValueSimple(String value);
	public Enum<ContactUse> getUse();
	public void setUse(Enum<ContactUse> value);
	public ContactUse getUseSimple();
	public void setUseSimple(ContactUse value);
	public Period getPeriod();
	public void setPeriod(Period value);
}
