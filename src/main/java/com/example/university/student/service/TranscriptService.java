package com.example.university.student.service;

import com.example.university.course.entity.Course;
import com.example.university.course.repository.CourseRepository;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.grade.entity.Grade;
import com.example.university.grade.repository.GradeRepository;
import com.example.university.student.dto.CourseGradeDto;
import com.example.university.student.dto.TranscriptResponse;
import com.example.university.student.entity.Student;
import com.example.university.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TranscriptService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GradeRepository gradeRepository;

    public TranscriptResponse getStudentTranscript(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));


        List<Grade> grades = gradeRepository.findByStudentId(studentId);

        List<CourseGradeDto> courseGrades = new ArrayList<>();
        double totalScore = 0;

        for (Grade grade : grades) {

            Course course = courseRepository.findById(grade.getCourseId())
                    .orElse(new Course());

            int score = grade.getScore() != null ? grade.getScore() : 0;
            String letterGrade = calculateLetterGrade(score);

            courseGrades.add(new CourseGradeDto(
                    course.getCourseName() != null ? course.getCourseName() : "Unknown Course",
                    course.getCredits() != null ? course.getCredits() : 3,
                    score,
                    letterGrade
            ));

            totalScore += score;
        }

        double gpa = courseGrades.isEmpty() ? 0.0 : totalScore / courseGrades.size();

        return new TranscriptResponse(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                courseGrades,
                gpa
        );
    }


    private String calculateLetterGrade(int score) {
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }
}