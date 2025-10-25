package com.asico.hr.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserModel {

    private String phoneNumber;
    private String name;
    private String codemeli;
    private String codeshenasname;
    private String fathername;
    private String BDplace;
    private String bdate_year;
    private String bdate_month;
    private String bdate_day;
    private String email;
    private String issuyear;
    private String issumonth;
    private String issuday;
    private String military;
    private String ostan;
    private String city;
    private String Address;
    private String bio;
    private String post;
    private String nationality;
    private String religion;
    private String telegram;

}
