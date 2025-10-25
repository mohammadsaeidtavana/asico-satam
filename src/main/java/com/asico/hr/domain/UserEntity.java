package com.asico.hr.domain;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class UserEntity extends BaseEntity<Long> {

    @Column(name = "USER_PHONE_NUMBER" ,unique = true)
    private String phoneNumber;
    @Column(name = "USER_NAME")
    private String name;
    @Column(name = "USER_EMAIL")
    private String email;
    @Column(name = "USER_BD_YEAR")
    private String bdate_year;
    @Column(name = "USER_BD_MONTH")
    private String bdate_month;
    @Column(name = "USER_BD_DAY")
    private String bdate_day;
    @Column(name = "USER_ABOUT")
    private String about_me;
    @Column(name = "USER_WEBSITE")
    private String website;
    @Column(name = "USER_GITHUB")
    private String github;
    @Column(name = "USER_LINKEDIN")
    private String linkedin;
    @Column(name = "USER_TELEGRAM")
    private String telegram;


    @Column(name = "USER_codemeli")
    private String codemeli;
    @Column(name = "USER_codeshenasname")
    private String codeshenasname;
    @Column(name = "USER_fathername")
    private String fathername;
    @Column(name = "USER_BDplace")
    private String BDplace;
    @Column(name = "USER_issuyear")
    private String issuyear;
    @Column(name = "USER_issumonth")
    private String issumonth;
    @Column(name = "USER_issuday")
    private String issuday;
    @Column(name = "USER_military")
    private String military;
    @Column(name = "USER_ostan")
    private String ostan;
    @Column(name = "USER_city")
    private String city;
    @Column(name = "USER_Address")
    private String Address;
    @Column(name = "USER_bio")
    private String bio;
    @Column(name = "USER_post")
    private String post;
    @Column(name = "USER_nationality")
    private String nationality;
    @Column(name = "USER_religion")
    private String religion;

}
