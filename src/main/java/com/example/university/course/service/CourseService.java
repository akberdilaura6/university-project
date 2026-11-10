package com.example.university.course.service;

import com.example.university.course.entity.Course;
import com.example.university.course.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(transactionManager = "courseTransactionManager")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional(transactionManager = "courseTransactionManager")
    public Course saveCourse(Course course) {
        if (courseRepository.existsByCourseCode(courseCodeOrThrow(course))) {
            throw new IllegalArgumentException("Course with code " + course.getCourseCode() + " already exists!");
        }
        return courseRepository.save(course);
    }

    private String courseCodeOrThrow(Course course) {
        return course.getCourseCode();
    }
}