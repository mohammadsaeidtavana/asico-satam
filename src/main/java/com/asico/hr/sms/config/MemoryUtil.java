package com.asico.hr.sms.config;

import lombok.extern.slf4j.Slf4j;


/**
 *
 * @author m.tavana
 * @since 2024
 */
@Slf4j
public class MemoryUtil {

    private static Long expiryTime = 120000L;


    public static TimedHashMap<String, String> smsOtp = new TimedHashMap<>(expiryTime);


    public static void putValue(String phone, String otpCode) {
            smsOtp.put(phone, otpCode);

        log.info("Values : " + String.valueOf(smsOtp.get(phone)));
    }

    public static String getValue(String phoneNumber) {

        log.info("Values : " + String.valueOf(smsOtp.get(phoneNumber)));
        return smsOtp.get(phoneNumber);
    }


    public static void clear(String phone) {
        smsOtp.remove(phone);
    }


}
