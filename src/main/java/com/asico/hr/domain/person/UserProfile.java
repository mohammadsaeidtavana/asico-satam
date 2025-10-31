package com.asico.hr.domain.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserProfile {

    @JsonProperty("picture")
    private MultipartFile picture;
    @JsonProperty("fullname")
    private String fullname;
    @JsonProperty("email2")
    private String email2;
    @JsonProperty("codemeli")
    private String codemeli;
    @JsonProperty("codeshenasname")
    private Long codeshenasname;
    @JsonProperty("fathername")
    private String fathername;
    @JsonProperty("BDplace")
    private String BDplace;


    // فایل‌ها
    @JsonProperty("birthCertificateFile")
    private MultipartFile birthCertificateFile;        // صفحه اول شناسنامه
    @JsonProperty("birthCertificatePage2File")
    private MultipartFile birthCertificatePage2File;   // صفحه دوم شناسنامه
    @JsonProperty("birthCertificatePage3File")
    private MultipartFile birthCertificatePage3File;   // صفحه سوم شناسنامه (اختیاری)
    @JsonProperty("nationalCardFrontFile")
    private MultipartFile nationalCardFrontFile;       // روی کارت ملی
    @JsonProperty("nationalCardBackFile")
    private MultipartFile nationalCardBackFile;        // پشت کارت ملی
    @JsonProperty("militaryCardFile")
    private MultipartFile militaryCardFile;            // کارت نظام وظیفه (اختیاری)

    @JsonProperty("BDyear")
    private Integer BDyear;   // سال تولد
    @JsonProperty("BDmonth")
    private Integer BDmonth;  // ماه تولد
    @JsonProperty("BDday")
    private Integer BDday;    // روز تولد


    @JsonProperty("BDissuyear")
    private Integer BDissuyear;  //صدور شناسنانه
    @JsonProperty("BDissumonth")
    private Integer BDissumonth;  //صدور شناسنانه
    @JsonProperty("BDissuday")
    private Integer BDissuday;  //صدور شناسنانه


    @JsonProperty("sex")
    // جنسیت
    private String sex;

    @JsonProperty("military")
    // وضعیت نظام وظیفه
    private String military;

    @JsonProperty("city")
    // استان محل سکونت
    private String city;

    @JsonProperty("province")
    // شهر محل سکونت
    private String province;

    @JsonProperty("mariage")
    // وضعیت تاهل
    private String mariage;

    @JsonProperty("nationalityM")
    // ملیت
    private String nationalityM;

    @JsonProperty("address")
    // آدرس محل سکونت
    @NotBlank(message = "آدرس نمی‌تواند خالی باشد")
    @Size(max = 500, message = "آدرس نمی‌تواند بیشتر از 500 کاراکتر باشد")
    private String address;


    @JsonProperty("post")
    private String post;          // کد پستی
    @JsonProperty("nationality")
    private String nationality;   // تابعیت
    @JsonProperty("religion")
    private String religion;      // دین

    @JsonProperty("isgoverment")
    private Boolean isgoverment; // آیا کارمند دولت است یا خیر
    @JsonProperty("isretired")
    private Boolean isretired; // آیا بازنشسته است یا خیر
    @JsonProperty("isasico")
    private Boolean isasico; // آیا تحت پوشش بیمه تامین اجتماعی است یا خیر
    @JsonProperty("sandoghCompanyName")
    private String sandoghCompanyName; // نام شرکت در صندوق
    @JsonProperty("insuranceHistoryCount")
    private Integer insuranceHistoryCount; // مدت سابقه کار بیمه‌ای به ماه
    @JsonProperty("notinsuranceCount")
    private Integer notinsuranceCount; // مدت بدون سابقه بیمه‌ای به ماه

    @JsonProperty("insuranceHistoryFile")
    private MultipartFile insuranceHistoryFile; // فایل سابقه بیمه‌ای

    @JsonProperty("educations")
    private List<Education> educations = new ArrayList<>();
    @JsonProperty("experiences")
    private List<Experience> experiences = new ArrayList<>();
    @JsonProperty("courses")
    private List<Course> courses = new ArrayList<>();
    @JsonProperty("skills")
    private List<Skill> skills = new ArrayList<>();
    @JsonProperty("researches")
    private List<Research> researches = new ArrayList<>();
    @JsonProperty("expertises")
    private List<Expertise> expertises = new ArrayList<>();
    @JsonProperty("personalExperiences")
    private List<PersonalExperience> personalExperiences = new ArrayList<>();

    @JsonProperty("resumeFile")
    private MultipartFile resumeFile; // فایل رزومه




}
