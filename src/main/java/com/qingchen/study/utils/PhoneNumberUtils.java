package com.qingchen.study.utils;


import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 电话号码相关的工具类
 * 
 * @author JUNCAIFEIYANG
 *
 */
public class PhoneNumberUtils {
	static final int MAX_LENGTH_COUNTRY_CODE = 4;

	/**
	 * 检查手机是否是国际的
	 * 
	 * @param countryCode
	 * @return
	 */
	public static boolean checkMobileInternation(String countryCode) {
		if (StringUtils.isEmpty(countryCode)) {
			return false;
		}
		if ("86".equals(countryCode) || "+86".equals(countryCode)) {
			return false;
		}
		return true;
	}

	/**
	 * 检查是否是手机号
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isMobile(String value) {
		if (value == null) {
			return false;
		}
		String mobileNumber = format(value,"");
		if(StringUtils.isEmpty(mobileNumber)){
			return false;
		}
		if(mobileNumber.startsWith("+")){
			return isNumeric(mobileNumber.substring(1));
		}else{
			return isNumeric(mobileNumber);
		}
	}
	
	/**
	 * 检查国内手机号格式
	 * 只判断以1开头，长度为11，都为数字
	 * @param phone
	 * @return
	 */
	public static boolean checkChineseMobile(String phone) {
		if (StringUtils.isEmpty(phone)) {
			return false;
		}
		String regex = "[1][0-9]{10}$";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(phone);
		return m.matches();
	}

	public static String format(String mobileNumber, String countryCode) {
		if(StringUtils.isEmpty(mobileNumber)){
			return null;
		}
		if(mobileNumber.startsWith("+")){
			if(mobileNumber.startsWith("+86")){
				return mobileNumber.substring(3);
			}else{
				return mobileNumber;
			}
		}
		if (StringUtils.isEmpty(countryCode)) {
			return mobileNumber;
		} else {
			if (checkMobileInternation(countryCode)) {
				if (countryCode.startsWith("+")) {
					return countryCode + mobileNumber;
				} else {
					return "+" + countryCode + mobileNumber;
				}
			} else {
				return mobileNumber;
			}
		}
	}

	public static PhoneNumber parser(String numberStr) {
		if (numberStr.startsWith("+")) {
			// 提交国际码
			int code = getCountryCode(numberStr);
			if (code > 0) {
				String phoneNumber = numberStr.substring(String.valueOf(code).length() + 1);
				return new PhoneNumber(String.valueOf(code), phoneNumber);
			}
			return null;
		} else {
			// 默认为国内电话
			return new PhoneNumber("86", numberStr);
		}

	}

	private static int getCountryCode(String numberStr) {
		if (StringUtils.isEmpty(numberStr)) {
			return 0;
		}
		if (!numberStr.startsWith("+")) {
			return 0;
		}
		String fullNumber = numberStr.substring(1);
		int countryCode;
		int numberLength = fullNumber.length();
		Map<Integer, List<String>> countryCodeMap = new CountryCodeMap().getCountryCodeMap();
		int start = MAX_LENGTH_COUNTRY_CODE;
		if (start > numberLength) {
			start = numberLength;
		}
		for (int i = start; i > 0; i--) {
			countryCode = Integer.parseInt(fullNumber.substring(0, i));
			if (countryCodeMap.containsKey(countryCode)) {
				return countryCode;
			}
		}
		return 0;
	}

	/**
	 * 优化国家码
	 * @param countryCode
	 * @return
	 */
	public static String optimizeCountryCode(String countryCode) {
		if (StringUtils.isNotEmpty(countryCode)) {
			countryCode = countryCode.replace("+", "");
		}
		// 考虑到countryCode可能是空串的情况,空串默认是国内
		return checkMobileInternation(countryCode) ? countryCode : "86";
	}
	
	public static boolean isNumeric(String str) {
		if(StringUtils.isEmpty(str)){
			return false;
		}
        Pattern pattern = Pattern.compile("[0-9]*");  
        Matcher isNum = pattern.matcher(str);  
        if (!isNum.matches()) {  
            return false;  
        }  
        return true;  
    }

	public static void main(String[] args) {
//		System.out.println(parser("+852123456789"));
//		System.out.println(parser("+1123456789"));
//		System.out.println(parser("+1808123456789"));
//		System.out.println(parser("13502098055"));		
//		System.out.println(format("123456789","852"));
//		System.out.println(format("13502098066","86"));
//		System.out.println(format("+8613502098066","86"));
//		System.out.println(format("+85213502098066","852"));

		System.out.println(isMobile("+852123456789"));
		System.out.println(isMobile("+1123456789"));
		System.out.println(isMobile("+180812 3456789"));
		System.out.println(isMobile("13502098055"));		
		System.out.println(isMobile("123456789"));
		System.out.println(isMobile("13502098066"));
		System.out.println(isMobile("+8613502098066"));
		System.out.println(isMobile(" +85213502098066"));
}
}
