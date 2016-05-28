/**
 * Copyright (c) 2014 Innominds. All Rights Reserved.
 */
package com.innominds.persistence.constants;

/**
 * Gender.java
 * 
 * @author THIRUPATHIREDDY VAJJALA
 *
 */
public enum Gender {

	/**
	 * Enumeration value for MALE
	 */
	MALE((short) 2),

	/**
	 * Enumeration value for FEMALE
	 */
	FEMALE((short) 1),

	/**
	 * Enumeration value for OTHER
	 */
	OTHER((short) 0);

	Short status;

	Gender(Short status) {
		this.status = status;
	}
}
