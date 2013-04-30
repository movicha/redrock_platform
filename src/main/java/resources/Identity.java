package resources;

import java.util.Date;

public interface Identity extends Element {
	public String getGtin();
	public void setGtin(String value);
	public String getGtinSimple();
	public void setGtinSimple(String value);
	public String getLot();
	public void setLot(String value);
	public String getLotSimple();
	public void setLotSimple(String value);
	public String getSerialNumber();
	public void setSerialNumber(String value);
	public String getSerialNumberSimple();
	public void setSerialNumberSimple(String value);
	public Date getExpiry();
	public void setExpiry(Date value);
	public String getExpirySimple();
	public void setExpirySimple(String value);
}
