package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import resources.Extension;

public class AddressImpl implements Address {
    /**
     * Identifies the intended purpose of this address
     */
    private Enum<AddressUse> use;

    /**
     * a full text representation of the address
     */
    private String text;

    /**
     * A line of an address (typically used for street names & numbers, unit details, delivery hints, etc.) .
     */
    private List<String> line = new ArrayList<String>();

    /**
     * The name of the city, town, village or other community or delivery centre.
     */
    private String city;

    /**
     * Sub-unit of a country with limited sovereignty in a federally organized country. A code may be used if codes are in common use (i.e. US 2 letter state codes).
     */
    private String state;

    /**
     * A postal code designating a region defined by the postal service.
     */
    private String zip;

    /**
     * Country. ISO 3166 3 letter codes can be used in place of a full country name.
     */
    private String country;

    /**
     * Time period when address was/is in use
     */
    private Period period;

    public Enum<AddressUse> getUse() { 
      return this.use;
    }

    public void setUse(Enum<AddressUse> value) { 
      this.use = value;
    }

    public AddressUse getUseSimple() { 
      return this.use == null ? null : this.use.valueOf(AddressUse.class, this.use.name());
    }

    public void setUseSimple(AddressUse value) { 
      if (value == null)
        this.use = null;
      else
        this.use= value;
    }

    public String getText() { 
      return this.text;
    }

    public void setText(String value) { 
      this.text = value;
    }

    public String getTextSimple() { 
      return this.text == null ? null : this.text;
    }

    public void setTextSimple(String value) { 
      if (value == null)
        this.text = null;
      else {
        if (this.text == null)
          this.text = new String();
        this.text= value;
      }
    }

    public List<String> getLine() { 
      return this.line;
    }

    public String getCity() { 
      return this.city;
    }

    public void setCity(String value) { 
      this.city = value;
    }

    public String getCitySimple() { 
      return this.city == null ? null : this.city;
    }

    public void setCitySimple(String value) { 
      if (value == null)
        this.city = null;
      else {
        if (this.city == null)
          this.city = new String();
        this.city = value;
      }
    }

    public String getState() { 
      return this.state;
    }

    public void setState(String value) { 
      this.state = value;
    }

    public String getStateSimple() { 
      return this.state == null ? null : this.state;
    }

    public void setStateSimple(String value) { 
      if (value == null)
        this.state = null;
      else {
        if (this.state == null)
          this.state = new String();
        this.state = value;
      }
    }

    public String getZip() { 
      return this.zip;
    }

    public void setZip(String value) { 
      this.zip = value;
    }

    public String getZipSimple() { 
      return this.zip == null ? null : this.zip;
    }

    public void setZipSimple(String value) { 
      if (value == null)
        this.zip = null;
      else {
        if (this.zip == null)
          this.zip = new String();
        this.zip = value;
      }
    }

    public String getCountry() { 
      return this.country;
    }

    public void setCountry(String value) { 
      this.country = value;
    }

    public String getCountrySimple() { 
      return this.country == null ? null : this.country;
    }

    public void setCountrySimple(String value) { 
      if (value == null)
        this.country = null;
      else 
        this.country = value; 
    }

    public Period getPeriod() { 
      return this.period;
    }

    public void setPeriod(Period value) { 
      this.period = value;
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


}
