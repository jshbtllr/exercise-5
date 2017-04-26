/*
 * Value
 *
 * Value Object Creation
 *
 */
package com.exercise5.model;

public class Value {
	private String value;
	public Value(String value) {
		this.value = value;
	}
	
	public void setValue(String input) {
		this.value = input;
	}
	
	public String getValue() {
		return this.value;
	}
}
