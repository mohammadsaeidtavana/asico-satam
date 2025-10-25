package com.asico.hr.sms.domain;

/**
 * @author m.tavana
 * @since 2025\
 */
public enum OtpType {
    UNDEFINED(0),
    TEXT(1),
    VOICE(2);

    private int type;

    OtpType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
