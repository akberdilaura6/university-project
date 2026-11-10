package com.example.university.grade.controller;

import com.example.university.grade.entity.Grade;
import com.example.university.grade.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
    private final GradeService service;

    public GradeController(GradeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Grade> list(@RequestParam(required = false) Long studentId) {
        if (studentId != null) {
            return service.findByStudentId(studentId);
        }
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Grade create(@Valid @RequestBody Grade g) {
        return service.create(g);
    }

    @PutMapping("/{id}")
    public Grade update(@PathVariable Long id, @Valid @RequestBody Grade g) {
        return service.update(id, g);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}