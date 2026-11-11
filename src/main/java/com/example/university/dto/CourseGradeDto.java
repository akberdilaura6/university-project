package com.example.university.student.dto;

public class CourseGradeDto {
    private String courseName;
    private int credits;
    private double score;
    private String letterGrade;

    public CourseGradeDto(String courseName, int credits, double score, String letterGrade) {
        this.courseName = courseName;
        this.credits = credits;
        this.score = score;
        this.letterGrade = letterGrade;
    }

    public String getCourseName() { return courseName; }
    public int getCredits() { return credits; }
    public double getScore() { return score; }
    public String getLetterGrade() { return letterGrade; }
}