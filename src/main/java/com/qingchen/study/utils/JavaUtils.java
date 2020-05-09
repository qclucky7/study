package com.qingchen.study.utils;

public class JavaUtils {

    /**
     * 隐藏电话的中间部分
     *
     * @return 返回中间部分被隐藏的字符串
     */

    public static String getPhoneHide(String number) {

        StringBuilder buffer = new StringBuilder();
        if (number.length() > 10) {
            for (int i = 0; i < number.length(); i++) {
                if (i > 2 && i < 7) {
                    buffer.append("*");
                    continue;
                }
                buffer.append(number.charAt(i));
            }
        }
        return buffer.toString();

    }


    /**
     * 隐藏身份证的中间部分
     *
     * @param idCard
     * @return
     */

    public static String getIdCardHide(String idCard) {

        StringBuffer buffer = new StringBuffer("");
        if (idCard.length() > 15) {
            for (int i = 0; i < idCard.length(); i++) {
                if (i > 3 && i < 14) {
                    buffer.append("*");
                    continue;
                }
                buffer.append(idCard.charAt(i));

            }

        }
        return buffer.toString();

    }


    /**
     * @param str 判断一个字符串是不是纯字母
     * @return
     */

    public static boolean justAz(String str) {

        String reg = "^[a-zA-Z\\s]*$";
        return str.matches(reg);

    }


    /**
     * @param str 判断一个字符串是不是纯数字
     * @return
     */

    public static boolean justNumber(String str) {

        String reg = "^[0-9\\.]*$";
        return str.matches(reg);

    }

    /**
     * @param str 判断一个字符串是不是只有数字 字母和/
     * @return
     */
    public static boolean justNumAz(String str) {

        String reg = "^[A-Za-z0-9/]+$";
        return str.matches(reg);

    }
}
