package com.asico.hr.payment.Config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemoryUtil {

    private static Long expiryTime = 36000000L; // 10 minute


    public static TimedHashMap<String, String> userSession = new TimedHashMap<>(expiryTime);


    public static void putValue(String authority, String phone) {
        if (!userSession.containsKey(authority)) {
            userSession.put(authority, phone);

            log.info("Values : " + String.valueOf(userSession.get(phone)));
        }


    }

    public static String getValue(String authority) {

        log.info("Values : " + String.valueOf(userSession.get(authority)));
        return userSession.get(authority);
    }


    public static void clear(String authority) {
        userSession.remove(authority);
    }


}
