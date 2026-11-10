package com.example.university.grade.service;

import com.example.university.exception.ResourceNotFoundException;
import com.example.university.grade.entity.Grade;
import com.example.university.grade.repository.GradeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GradeService {
    private final GradeRepository repo;

    public GradeService(GradeRepository repo) {
        this.repo = repo;
    }

    public List<Grade> findAll() {
        return repo.findAll();
    }

    public List<Grade> findByStudentId(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    @Transactional(transactionManager = "gradeTransactionManager")
    public Grade create(Grade g) {
        return repo.save(g);
    }

    @Transactional(transactionManager = "gradeTransactionManager")
    public Grade update(Long id, Grade data) {
        Grade g = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade " + id + " not found"));
        g.setStudentId(data.getStudentId());
        g.setCourseId(data.getCourseId());
        g.setScore(data.getScore());
        return repo.save(g);
    }

    @Transactional(transactionManager = "gradeTransactionManager")
    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Grade " + id + " not found");
        repo.deleteById(id);
    }
}