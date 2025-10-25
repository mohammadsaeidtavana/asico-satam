package com.asico.hr.domain;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COURSE")
public class CourseEntity extends BaseEntity<Long> {

    @Column(name = "COURSE_NAME")
    private String name;
    @Column(name = "COURSE_ID")
    private String courseId;
    @Column(name = "COURSE_SP_ID")
    private String spotPlayerCourseId;
    @Column(name = "COURSE_PRICE")
    private long price;
    @Column(name = "COURSE_DISCOUNT_PRICE")
    private long discountPrice;
    @Column(name = "COURSE_LESSON")
    private String lessons;
    @Column(name = "COURSE_DURATION")
    private String courseDuration;

    @Column(name = "COURSE_PAGE")
    private String coursePage;

    @Column(name = "COURSE_LECTURER")
    private String lecturer;

    @Column(name = "COURSE_LECTURER_PICTURE")
    private String lecturerPicture;

    @Column(name = "COURSE_PICTURE")
    private String picturePath;


    @Column(name = "COURSE_VIDEO")
    private String videoPath;


    @Column(name = "COURSE_SUPPORT_TELEGRAM")
    private String supportTelegram;


    @Column(name = "COURSE_ACCESS_SUPPORT_TELEGRAM")
    private String accessSupportTelegram;

    @Column(name = "COURSE_TELEGRAM_CHANNEL")
    private String telegramChannel;

    @Column(name = "COURSE_STATUS")
    private String courseStatus;

    @Column(name = "COURSE_STATE")
    private boolean courseState;

    @Column(name = "COURSE_TYPE")
    private String courseType;

    @Column(name = "COURSE_CREATION_DATE")
    private String courseCreationDate;

    @Column(name = "COURSE_UPDATE_DATE")
    private String courseUpdateDate;

    @Column(name = "COURSE_STUDENT_COUNT")
    private String courseStudentCount;





    @Column(name = "COURSE_BOLIAN_STATUS")
    @Enumerated(EnumType.ORDINAL)
    private CourseStatus status;

//    @OneToMany(mappedBy = "CourseEntity" ,cascade =  CascadeType.ALL ,fetch = FetchType.EAGER)
//    private List<CommentEntity> comments;

}
