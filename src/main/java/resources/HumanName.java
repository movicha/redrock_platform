package resources;

import java.util.ArrayList;

import models.Period;

public interface HumanName extends Type {
	public enum NameUse {
		usual, // Known as/conventional/the one you normally use
		official, // The formal name as registered in an official (government)
					// registry, but which name might not be commonly used. May
					// be called "legal name".
		temp, // A temporary name. A name valid time can provide more detailed
				// information. This may also be used for temporary names
				// assigned at birth or in emergency situations.
		nickname, // A name that is used to address the person in an informal
					// manner, but is not part of their formal or usual name
		anonymous, // Anonymous assigned name, alias, or pseudonym (used to
					// protect a person's identity for privacy reasons)
		old, // This name is no longer in use (or was never correct, but
				// retained for records)
		maiden, // A name used prior to marriage. Marriage naming customs vary
				// greatly around the world. This name use is for use by
				// applications that collect and store "maiden" names. Though
				// the concept of maiden name is often gender specific, the use
				// of this term is not gender specific. The use of this term
				// does not imply any particular history for a person's name,
				// nor should the maiden name be determined algorithmically.
		Null; // added to help the parsers
		public static NameUse fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("usual".equals(codeString))
				return usual;
			if ("official".equals(codeString))
				return official;
			if ("temp".equals(codeString))
				return temp;
			if ("nickname".equals(codeString))
				return nickname;
			if ("anonymous".equals(codeString))
				return anonymous;
			if ("old".equals(codeString))
				return old;
			if ("maiden".equals(codeString))
				return maiden;
			throw new Exception("Unknown NameUse code '" + codeString + "'");
		}

		public String toCode() {
			switch (this) {
			case usual:
				return "usual";
			case official:
				return "official";
			case temp:
				return "temp";
			case nickname:
				return "nickname";
			case anonymous:
				return "anonymous";
			case old:
				return "old";
			case maiden:
				return "maiden";
			default:
				return "?";
			}
		}
	}

	public class NameUseEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("usual".equals(codeString))
				return NameUse.usual;
			if ("official".equals(codeString))
				return NameUse.official;
			if ("temp".equals(codeString))
				return NameUse.temp;
			if ("nickname".equals(codeString))
				return NameUse.nickname;
			if ("anonymous".equals(codeString))
				return NameUse.anonymous;
			if ("old".equals(codeString))
				return NameUse.old;
			if ("maiden".equals(codeString))
				return NameUse.maiden;
			throw new Exception("Unknown NameUse code '" + codeString + "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == NameUse.usual)
				return "usual";
			if (code == NameUse.official)
				return "official";
			if (code == NameUse.temp)
				return "temp";
			if (code == NameUse.nickname)
				return "nickname";
			if (code == NameUse.anonymous)
				return "anonymous";
			if (code == NameUse.old)
				return "old";
			if (code == NameUse.maiden)
				return "maiden";
			return "?";
		}
	}

	public Enum<NameUse> getUse();

	public void setUse(Enum<NameUse> value);

	public String getText();

	public void setText(String text);

	public ArrayList<String> getFamily();

	public void setFamily(ArrayList<String> family);

	public ArrayList<String> getGiven();

	public void setGiven(ArrayList<String> given);

	public ArrayList<String> getPrefix();

	public void setPrefix(ArrayList<String> prefix);

	public ArrayList<String> getSuffix();

	public void setSuffix(ArrayList<String> suffix);

	public Period getPeriod();

	public void setPeriod(Period period);
}
