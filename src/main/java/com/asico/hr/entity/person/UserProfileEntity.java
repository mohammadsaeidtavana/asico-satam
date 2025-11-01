package com.asico.hr.entity.person;

import com.asico.hr.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_profiles")
public class UserProfileEntity extends BaseEntity<Long> {

    @Column(name = "picture", length = 500)
    private String picture;
    @Column(name = "fullname", nullable = false, length = 150)
    private String fullname;
    @Column(name = "email2", length = 150)
    private String email2;
    @Column(name = "codemeli", length = 20)
    private String codemeli;
    @Column(name = "codeshenasname")
    private Long codeshenasname;
    @Column(name = "fathername", length = 100)
    private String fathername;
    @JsonProperty("BDplace")
    private String BDplace;


    // فایل‌ها
    @Column(name = "birth_certificate_file", length = 500)
    private String birthCertificateFile;        // صفحه اول شناسنامه
    @Column(name = "birth_certificate_page2_file", length = 500)
    private String birthCertificatePage2File;   // صفحه دوم شناسنامه
    @Column(name = "birth_certificate_page3_file", length = 500)
    private String birthCertificatePage3File;   // صفحه سوم شناسنامه (اختیاری)
    @Column(name = "national_card_front_file", length = 500)
    private String nationalCardFrontFile;       // روی کارت ملی
    @Column(name = "national_card_back_file", length = 500)
    private String nationalCardBackFile;        // پشت کارت ملی
    @Column(name = "military_card_file", length = 500)
    private String militaryCardFile;            // کارت نظام وظیفه (اختیاری)

    @Column(name = "birth_year")
    private Integer BDyear;   // سال تولد
    @Column(name = "birth_month")
    private Integer BDmonth;  // ماه تولد
    @Column(name = "birth_day")
    private Integer BDday;    // روز تولد


    @Column(name = "birth_issue_year")
    private Integer BDissuyear;  //صدور شناسنانه
    @Column(name = "birth_issue_month")
    private Integer BDissumonth;  //صدور شناسنانه
    @Column(name = "birth_issue_day")
    private Integer BDissuday;  //صدور شناسنانه


    @Column(name = "sex", length = 10)
    // جنسیت
    private String sex;

    @Column(name = "military_status", length = 100)
    // وضعیت نظام وظیفه
    private String military;

    @Column(name = "city", length = 100)
    // استان محل سکونت
    private String city;

    @Column(name = "province", length = 100)
    // شهر محل سکونت
    private String province;

    @Column(name = "mariage_status", length = 50)
    // وضعیت تاهل
    private String mariage;

    @Column(name = "nationality_m", length = 100)
    // ملیت
    private String nationalityM;

    @JsonProperty("address")
    @Column(name = "address", length = 500, nullable = false)
    @NotBlank(message = "آدرس نمی‌تواند خالی باشد")
    @Size(max = 500, message = "آدرس نمی‌تواند بیشتر از 500 کاراکتر باشد")
    private String address;


    @Column(name = "postal_code", length = 20)
    private String post;          // کد پستی
    @Column(name = "nationality", length = 100)
    private String nationality;   // تابعیت
    @Column(name = "religion", length = 100)
    private String religion;      // دین

    @Column(name = "is_government")
    private String isgoverment; // آیا کارمند دولت است یا خیر
    @Column(name = "is_retired")
    private String isretired; // آیا بازنشسته است یا خیر
    @Column(name = "is_asico")
    private String isasico; // آیا تحت پوشش بیمه تامین اجتماعی است یا خیر
    @Column(name = "sandogh_company_name", length = 200)
    private String sandoghCompanyName; // نام شرکت در صندوق
    @Column(name = "insurance_history_count")
    private Integer insuranceHistoryCount; // مدت سابقه کار بیمه‌ای به ماه
    @Column(name = "not_insurance_count")
    private Integer notinsuranceCount; // مدت بدون سابقه بیمه‌ای به ماه

    @Column(name = "insurance_history_file", length = 500)
    private String insuranceHistoryFile; // فایل سابقه بیمه‌ای

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Education> educations = new ArrayList<>();
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Experience> experiences = new ArrayList<>();
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Course> courses = new ArrayList<>();
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Skill> skills = new ArrayList<>();
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Research> researches = new ArrayList<>();
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Expertise> expertises = new ArrayList<>();
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<PersonalExperience> personalExperiences = new ArrayList<>();

    @Column(name = "resume_file")
    private String resumeFile; // فایل رزومه


}
