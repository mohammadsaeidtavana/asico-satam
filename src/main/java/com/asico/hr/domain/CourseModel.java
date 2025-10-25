package com.asico.hr.domain;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseModel {


    private String name;

    private String courseId;

    private String spotPlayerCourseId;

    private long price;

    private long discountPrice;

    private String lessons;

    private String courseDuration;

    private String coursePage;

    private String lecturer;

    private String lecturerPicture;

    private String picturePath;

    private String videoPath;

    private CourseStatus status;

    private String supportTelegram;

    private String accessSupportTelegram;

    private String telegramChannel;

    private String courseStatus;

    private String courseType;


    private String courseCreationDate;

    private String courseStudentCount;

    private boolean courseState;

    private String courseUpdateDate;

//    private List<CommentModel> comments;

}
