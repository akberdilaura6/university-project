package com.example.university.student.dto;

import java.util.List;

public class TranscriptResponse {
    private Long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private List<CourseGradeDto> courses;
    private double gpa; // орташа бал (Grade Point Average)


    public TranscriptResponse(Long studentId, String firstName, String lastName, String email, List<CourseGradeDto> courses, double gpa) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courses = courses;
        this.gpa = gpa;
    }


    public Long getStudentId() { return studentId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public List<CourseGradeDto> getCourses() { return courses; }
    public double getGpa() { return gpa; }
}