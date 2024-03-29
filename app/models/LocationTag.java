/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs;

/**
 * An LocationTag allows categorization of {@link Location}s
 * 
 * @see Location
 * @since 1.5
 */
public class LocationTag extends BaseOpenmrsMetadata implements java.io.Serializable {
	
	public static final long serialVersionUID = 7654L;
	
	private Integer locationTagId;
	
	// Constructors
	
	/** default constructor */
	public LocationTag() {
	}
	
	/** constructor with id */
	public LocationTag(Integer locationTagId) {
		this.locationTagId = locationTagId;
	}
	
	/**
	 * Required values constructor. This is the minimum number of values that must be non-null in
	 * order to have a successful save to the database
	 * 
	 * @param name the name of this encounter type
	 * @param description a short description of why this encounter type exists
	 */
	public LocationTag(String name, String description) {
		setName(name);
		setDescription(description);
	}
	
	// Property accessors
	
	/**
	 * @return Returns the locationTagId.
	 */
	public Integer getLocationTagId() {
		return locationTagId;
	}
	
	/**
	 * @param locationTagId The locationTagId to set.
	 */
	public void setLocationTagId(Integer locationTagId) {
		this.locationTagId = locationTagId;
	}
	
	/**
	 * @return Returns the tag.
	 * @deprecated use {@link #getName()} instead
	 */
	@Deprecated
	public String getTag() {
		return getName();
	}
	
	/**
	 * @param tag The tag to set.
	 * @deprecated use {@link #setName(String)} instead
	 */
	@Deprecated
	public void setTag(String tag) {
		setName(tag);
	}
	
	public String toString() {
		return getName();
	}
	
	/**
	 * @see org.openmrs.OpenmrsObject#getId()
	 */
	public Integer getId() {
		return getLocationTagId();
	}
	
	/**
	 * @see org.openmrs.OpenmrsObject#setId(java.lang.Integer)
	 */
	public void setId(Integer id) {
		setLocationTagId(id);
		
	}
}
