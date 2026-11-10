package com.example.university.student.service;

import com.example.university.exception.DuplicateResourceException;
import com.example.university.exception.ResourceNotFoundException;
import com.example.university.student.entity.Student;
import com.example.university.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> findAll() {
        return repo.findAll();
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public Student create(Student s) {
        if (repo.existsByEmail(s.getEmail()))
            throw new DuplicateResourceException("Email already exists");
        return repo.save(s);
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public Student update(Long id, Student data) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student " + id + " not found"));
        s.setFirstName(data.getFirstName());
        s.setLastName(data.getLastName());
        s.setEmail(data.getEmail());
        s.setMajor(data.getMajor());
        s.setEnrollmentYear(data.getEnrollmentYear());
        return s;
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Student " + id + " not found");
        repo.deleteById(id);
    }
}