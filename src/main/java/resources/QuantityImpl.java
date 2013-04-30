package resources;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import utilities.Uri;
import utilities.UriImpl;

public class QuantityImpl implements Quantity {
    /**
     * The value of the measured amount. The value includes an implicit precision in the presentation of the value
     */
    private BigDecimal value;

    /**
     * How the value should be understood and represented - whether the actual value is greater or less than the stated value due to measurement issues. E.g. if the comparator is Ò<Ò , then the real value is < stated value
     */
    private QuantityComparator comparator;

    /**
     * A human readable form of the units
     */
    private String units;

    /**
     * The identification of the system that provides the coded form of the unit
     */
    private Uri system;

    /**
     * A computer processable form of the units in some unit representation system
     */
    private Code code;

    public BigDecimal getValue() { 
      return this.value;
    }

    public void setValue(BigDecimal value) { 
      this.value = value;
    }

    public BigDecimal getValueSimple() { 
      return this.value == null ? null : this.value;
    }

    public void setValueSimple(BigDecimal value) { 
      if (value == null)
        this.value = null;
      else {
        this.value = value;
      }
    }

    public QuantityComparator getComparator() { 
      return this.comparator;
    }

    public void setComparator(QuantityComparator value) { 
      this.comparator = value;
    }

    public QuantityComparator getComparatorSimple() { 
      return this.comparator == null ? null : this.comparator;
    }

    public void setComparatorSimple(QuantityComparator value) { 
      if (value == null)
        this.comparator = null;
      else {
        this.comparator = value;
      }
    }

    public String getUnits() { 
      return this.units;
    }

    public void setUnits(String value) { 
      this.units = value;
    }

    public String getUnitsSimple() { 
      return this.units == null ? null : this.units;
    }

    public void setUnitsSimple(String value) { 
      if (value == null)
        this.units = null;
      else {
        this.units = value;
      }
    }

    public Uri getSystem() { 
      return this.system;
    }

    public void setSystem(Uri value) { 
      this.system = value;
    }

    public Uri getSystemSimple() { 
      return this.system == null ? null : this.system;
    }

    public void setSystemSimple(Uri value) { 
      if (value == null)
        this.system = null;
      else {
        if (this.system == null)
          this.system = new UriImpl();
        this.system= value;
      }
    }

    public Code getCode() { 
      return this.code;
    }

    public void setCode(Code value) { 
      this.code = value;
    }

    public String getCodeSimple() { 
      return this.code == null ? null : this.code.getCodeText();
    }

    public void setCodeSimple(String value) { 
      if (value == null)
        this.code = null;
      else {
        if (this.code == null)
          this.code = new CodeImpl();
        this.code.setCodeText(value);
      }
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

	@Override
	public void setComparator(Enum<QuantityComparator> value) {
		// TODO Auto-generated method stub
		
	}

}


