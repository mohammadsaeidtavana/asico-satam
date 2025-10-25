package com.asico.hr.domain;
/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
public enum CourseStatus {
    ENABLE(0,"DISABLE"),
    DISABLE(1,"DISABLE");

    int code;
    String Status;

    CourseStatus(int code, String status) {
        this.code = code;
        Status = status;
    }
}
