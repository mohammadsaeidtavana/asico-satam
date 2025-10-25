package com.asico.hr.repository;

import com.asico.hr.domain.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mohammad saeid tavana
 * @version 1.0
 * @since 2024
 */
@Repository
@Transactional
public interface CourseRepository extends JpaRepository<CourseEntity,Long> {


    CourseEntity findByCourseId(String courseId);

    CourseEntity findByCoursePage(String coursePage);


}
