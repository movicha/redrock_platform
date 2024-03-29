package models;

import java.util.Date;

/**
 * Dates should be interpreted as follows: If startDate is null then the order has been going on
 * "since the beginning of time" Otherwise the order starts on startDate If discontinued is non-null
 * and true, then the following fields should be ignored: autoExpireDate if discontinuedDate is null
 * then the order was discontinued "the instant after it began" otherwise it was given from its
 * starting date until discontinuedDate Otherwise (discontinued is null or false) if autoExpireDate
 * is null, the order is set to go forever otherwise the order goes until autoExpireDate the
 * following fields should be ignored: discontinuedBy discontinuedDate discontinuedReason It is an
 * error to have discontinued be true and have discontinuedDate be after autoExpireDate. However
 * this is not checked for in the database or the application.
 * 
 * @version 1.0
 */
public class Order extends BasePhmData implements java.io.Serializable {
	
	public static final long serialVersionUID = 1L;
	
	public enum OrderAction {
		ORDER, DISCONTINUE
	}
	
	public enum Urgency {
		ROUTINE, STAT
	}
	
	private Integer orderId;
	
	private Patient patient;
	
	private Concept concept;
	
	/**
	 * This is an identifier generated for a given order and shared by all revisions (if any) of
	 * that order. The order number is passed to ancillary systems in order for results & events
	 * related to the order to be connected to the original order.
	 */
	private String orderNumber;
	
	/**
	 * Allows orders to be linked to a previous order - e.g., an order discontinue ampicillin linked
	 * to the original ampicillin order (the D/C gets its own order number)
	 */
	private String previousOrderNumber;
	
	/**
	 * Represents the action being taken on an order.
	 * 
	 * @see OrderAction
	 */
	private OrderAction orderAction = OrderAction.ORDER;
	
	/** When the order should begin. */
	private Date startDate;
	
	/** When the order should be discontinued if it hasn't already. */
	private Date autoExpireDate;
	
	private Encounter encounter;
	
	/**
	 * Free text instructions for the order (e.g., details about a referral, justification for a
	 * cardiac stress test, etc.)
	 */
	private String instructions;
	
	private Provider orderer;
	
	private Boolean discontinued = false;
	
	private User discontinuedBy;
	
	/** When the order was discontinued. */
	private Date discontinuedDate;
	
	private String discontinuedReason;
	
	private String accessionNumber;
	
	private Urgency urgency = Urgency.ROUTINE;
	
	// Constructors
	
	/** default constructor */
	public Order() {
	}
	
	/** constructor with id */
	public Order(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Order(Integer orderId, Patient patient, Concept concept) {
		this.setOrderId(orderId);
		this.setPatient(patient);
		this.setConcept(concept);
	}
	
	/**
	 * Performs a shallow copy of this Order. Does NOT copy orderId.
	 * 
	 * @return a shallow copy of this Order
	 */
	public Order copy() {
		return copyHelper(new Order());
	}
	
	/**
	 * The purpose of this method is to allow subclasses of Order to delegate a portion of their
	 * copy() method back to the superclass, in case the base class implementation changes.
	 * 
	 * @param target an Order that will have the state of <code>this</code> copied into it
	 * @return Returns the Order that was passed in, with state copied into it
	 */
	protected Order copyHelper(Order target) {
		target.setPatient(getPatient());
		target.setConcept(getConcept());
		target.setInstructions(getInstructions());
		target.setStartDate(getStartDate());
		target.setAutoExpireDate(getAutoExpireDate());
		target.setEncounter(getEncounter());
		target.setOrderer(getOrderer());
		target.setCreator(getCreator());
		target.setDateCreated(getDateCreated());
		target.setDiscontinued(getDiscontinued());
		target.setDiscontinuedDate(getDiscontinuedDate());
		target.setDiscontinuedReason(getDiscontinuedReason());
		target.setDiscontinuedBy(getDiscontinuedBy());
		target.setAccessionNumber(getAccessionNumber());
		target.setVoided(isVoided());
		target.setVoidedBy(getVoidedBy());
		target.setDateVoided(getDateVoided());
		target.setVoidReason(getVoidReason());
		target.setOrderNumber(getOrderNumber());
		target.setPreviousOrderNumber(getPreviousOrderNumber());
		target.setOrderAction(getOrderAction());
		target.setUrgency(getUrgency());
		
		return target;
	}
	
	/**
	 * Compares two objects for similarity
	 * 
	 * @param obj
	 * @return boolean true/false whether or not they are the same objects
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Order) {
			Order o = (Order) obj;
			if (this.getOrderId() != null && o.getOrderId() != null) {
				return (this.getOrderId().equals(o.getOrderId()));
			}
		}
		return false;
	}
	
	public int hashCode() {
		if (this.getOrderId() == null) {
			return super.hashCode();
		}
		return this.getOrderId().hashCode();
	}
	
	/**
	 * true/false whether or not this is a drug order overridden in extending class drugOrders.
	 */
	public boolean isDrugOrder() {
		return false;
	}
	
	// Property accessors
	
	/**
	 * @return Returns the autoExpireDate.
	 */
	public Date getAutoExpireDate() {
		return autoExpireDate;
	}
	
	/**
	 * @param autoExpireDate The autoExpireDate to set.
	 */
	public void setAutoExpireDate(Date autoExpireDate) {
		this.autoExpireDate = autoExpireDate;
	}
	
	/**
	 * @return Returns the concept.
	 */
	public Concept getConcept() {
		return concept;
	}
	
	/**
	 * @param concept The concept to set.
	 */
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	
	/**
	 * @return Returns the discontinued status.
	 * @should get discontinued property
	 */
	public Boolean getDiscontinued() {
		return discontinued;
	}
	
	/**
	 * @param discontinued The discontinued status to set.
	 */
	public void setDiscontinued(Boolean discontinued) {
		this.discontinued = discontinued;
	}
	
	/**
	 * @return Returns the discontinuedBy.
	 */
	public User getDiscontinuedBy() {
		return discontinuedBy;
	}
	
	/**
	 * @param discontinuedBy The discontinuedBy to set.
	 */
	public void setDiscontinuedBy(User discontinuedBy) {
		this.discontinuedBy = discontinuedBy;
	}
	
	/**
	 * @return Returns the discontinuedDate.
	 */
	public Date getDiscontinuedDate() {
		return discontinuedDate;
	}
	
	/**
	 * @param discontinuedDate The discontinuedDate to set.
	 */
	public void setDiscontinuedDate(Date discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}
	
	/**
	 * @return Returns the discontinuedReason.
	 */
	public String getDiscontinuedReason() {
		return discontinuedReason;
	}
	
	/**
	 * @param discontinuedReason The discontinuedReason to set.
	 */
	public void setDiscontinuedReason(String discontinuedReason) {
		this.discontinuedReason = discontinuedReason;
	}
	
	/**
	 * @return Returns the encounter.
	 */
	public Encounter getEncounter() {
		return encounter;
	}
	
	/**
	 * @param encounter The encounter to set.
	 */
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	
	/**
	 * @return Returns the instructions.
	 */
	public String getInstructions() {
		return instructions;
	}
	
	/**
	 * @param instructions The instructions to set.
	 */
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	
	/**
	 * @return Returns the accessionNumber.
	 */
	public String getAccessionNumber() {
		return accessionNumber;
	}
	
	/**
	 * @param accessionNumber The accessionNumber to set.
	 */
	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}
	
	/**
	 * @return Returns the orderer.
	 */
	public Provider getOrderer() {
		return orderer;
	}
	
	/**
	 * @param orderer The orderer to set.
	 */
	public void setOrderer(Provider orderer) {
		this.orderer = orderer;
	}
	
	/**
	 * @return Returns the orderId.
	 */
	public Integer getOrderId() {
		return orderId;
	}
	
	/**
	 * @param orderId The orderId to set.
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Convenience method to determine if order is current
	 * 
	 * @param checkDate - the date on which to check order. if null, will use current date
	 * @return boolean indicating whether the order was current on the input date
	 */
	public boolean isCurrent(Date checkDate) {
		if (isVoided()) {
			return false;
		}
		
		if (checkDate == null) {
			checkDate = new Date();
		}
		
		if (startDate != null && checkDate.before(startDate)) {
			return false;
		}
		
		if (discontinued != null && discontinued) {
			if (discontinuedDate == null) {
				return checkDate.equals(startDate);
			} else {
				return checkDate.before(discontinuedDate);
			}
			
		} else {
			if (autoExpireDate == null) {
				return true;
			} else {
				return checkDate.before(autoExpireDate);
			}
		}
	}
	
	public boolean isCurrent() {
		return isCurrent(new Date());
	}
	
	public boolean isFuture(Date checkDate) {
		if (isVoided()) {
			return false;
		}
		
		if (checkDate == null) {
			checkDate = new Date();
		}
		
		return startDate != null && checkDate.before(startDate);
	}
	
	public boolean isFuture() {
		return isFuture(new Date());
	}
	
	/**
	 * Convenience method to determine if order is discontinued at a given time
	 * 
	 * @param checkDate - the date on which to check order. if null, will use current date
	 * @return boolean indicating whether the order was discontinued on the input date
	 */
	public boolean isDiscontinued(Date checkDate) {
		if (isVoided()) {
			return false;
		}
		if (checkDate == null) {
			checkDate = new Date();
		}
		
		if (discontinued == null || !discontinued) {
			return false;
		}
		
		if (startDate == null || checkDate.before(startDate)) {
			return false;
		}
		if (discontinuedDate != null && discontinuedDate.after(checkDate)) {
			return false;
		}
		
		// guess we can't assume this has been filled correctly?
		/*
		 * if (discontinuedDate == null) { return false; }
		 */
		return true;
	}
	
	/**
	 * orderForm:jsp: <spring:bind path="order.discontinued" /> results in a call to
	 * isDiscontinued() which doesn't give access to the discontinued property so renamed it to
	 * isDiscontinuedRightNow which results in a call to getDiscontinued.
	 * 
	 * @since 1.5
	 */
	public boolean isDiscontinuedRightNow() {
		return isDiscontinued(new Date());
	}
	
	/**
	 * Gets the patient.
	 * 
	 * @return the patient.
	 */
	public Patient getPatient() {
		return patient;
	}
	
	/**
	 * Sets the patient.
	 * 
	 * @param patient the patient to set.
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	/**
	 * Gets the order number.
	 * 
	 * @return the order number.
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
	
	/**
	 * Sets the order number.
	 * 
	 * @param orderNumber the order number to set.
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	/**
	 * Gets the previous order number.
	 * 
	 * @return the previous order number.
	 */
	public String getPreviousOrderNumber() {
		return previousOrderNumber;
	}
	
	/**
	 * Sets the previous order number.
	 * 
	 * @param previousOrderNumber the previous order number to set.
	 */
	public void setPreviousOrderNumber(String previousOrderNumber) {
		this.previousOrderNumber = previousOrderNumber;
	}
	
	/**
	 * @return the orderAction
	 * @since 1.10
	 */
	public OrderAction getOrderAction() {
		return orderAction;
	}
	
	/**
	 * @param orderAction the orderAction to set
	 * @since 1.10
	 */
	public void setOrderAction(OrderAction orderAction) {
		this.orderAction = orderAction;
	}
	
	/**
	 * @since 1.5
	 * @see org.openmrs.OpenmrsObject#getId()
	 */
	public Integer getId() {
		return getOrderId();
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Order. orderId: " + orderId + " patient: " + patient + " orderType: " + getClass() + " concept: " + concept;
	}
	
	/**
	 * @since 1.5
	 * @see org.openmrs.OpenmrsObject#setId(java.lang.Integer)
	 */
	public void setId(Integer id) {
		setOrderId(id);
	}
	
	/**
	 * @return the urgency
	 * @since 1.10
	 */
	public Urgency getUrgency() {
		return urgency;
	}
	
	/**
	 * @param urgency the urgency to set
	 * @since 1.10
	 */
	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}
	
	/**
	 * Makes a copy of this order.
	 * 
	 * @return a copy of this order.
	 */
	public Order copyForModification() {
		Order copy = copyHelper(this);
		copy.orderNumber = null;
		copy.previousOrderNumber = this.orderNumber;
		return copy;
	}
}
