package com.qingchen.study.utils;

/**
 * 移动电话号码
 * 
 * @author JUNCAIFEIYANG
 *
 */
public class PhoneNumber {
	private String countryCode;
	private String phoneNumber;

	public PhoneNumber(String countryCode, String phoneNumber) {
		this.setCountryCode(countryCode);
		this.setPhoneNumber(phoneNumber);
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "PhoneNumber [countryCode=" + countryCode + ", phoneNumber=" + phoneNumber + "]";
	}

}
