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
@Table(name = "COMMENT")
public class CommentEntity extends BaseEntity<Long> {

    @Column(name = "COMMENT_NAME")
    private String name;
    @Column(name = "COMMENT_PHONENUMBER")
    private String phoneNumber;
    @Column(name = "COMMENT_TEXT")
    private String text;

//    @ManyToOne
//    @JoinColumn(name = "COURSE_ID", nullable = false)
//    private CourseEntity CourseEntity;


}
