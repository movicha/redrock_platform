package resources;

import java.math.BigDecimal;

import utilities.Uri;

public interface Quantity extends Type {
	public enum QuantityComparator {
		lessThan, // The actual value is less than the given value
		lessOrEqual, // The actual value is less than or equal to the given
						// value
		greaterOrEqual, // The actual value is greater than or equal to the
						// given value
		greaterThan, // The actual value is greater than the given value
		Null; // added to help the parsers

		public static QuantityComparator fromCode(String codeString)
				throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("<".equals(codeString))
				return lessThan;
			if ("<=".equals(codeString))
				return lessOrEqual;
			if (">=".equals(codeString))
				return greaterOrEqual;
			if (">".equals(codeString))
				return greaterThan;
			throw new Exception("Unknown QuantityComparator code '"
					+ codeString + "'");
		}

		public String toCode() {
			switch (this) {
			case lessThan:
				return "<";
			case lessOrEqual:
				return "<=";
			case greaterOrEqual:
				return ">=";
			case greaterThan:
				return ">";
			default:
				return "?";
			}
		}
		
		
	}

	public class QuantityComparatorEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("<".equals(codeString))
				return QuantityComparator.lessThan;
			if ("<=".equals(codeString))
				return QuantityComparator.lessOrEqual;
			if (">=".equals(codeString))
				return QuantityComparator.greaterOrEqual;
			if (">".equals(codeString))
				return QuantityComparator.greaterThan;
			throw new Exception("Unknown QuantityComparator code '"
					+ codeString + "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == QuantityComparator.lessThan)
				return "<";
			if (code == QuantityComparator.lessOrEqual)
				return "<=";
			if (code == QuantityComparator.greaterOrEqual)
				return ">=";
			if (code == QuantityComparator.greaterThan)
				return ">";
			return "?";
		}
	}



    public BigDecimal getValue();
    public void setValue(BigDecimal value);
    public BigDecimal getValueSimple();
    public void setValueSimple(BigDecimal value);
    public Enum<QuantityComparator> getComparator();
    public void setComparator(Enum<QuantityComparator> value);
    public QuantityComparator getComparatorSimple();
    public void setComparatorSimple(QuantityComparator value);
    public String getUnits();
    public void setUnits(String value);
    public String getUnitsSimple();
    public void setUnitsSimple(String value);
    public Uri getSystem();
    public void setSystem(Uri value);
    public Uri getSystemSimple();
    public void setSystemSimple(Uri value);
    public Code getCode();
    public void setCode(Code value);
    public String getCodeSimple();
    public void setCodeSimple(String value);
}
