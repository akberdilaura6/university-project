package com.example.university.student.controller;

import com.example.university.student.dto.TranscriptResponse;
import com.example.university.student.service.TranscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private TranscriptService transcriptService;

    // Бұрынғы басқа методтарың осында тұра береді...

    @GetMapping("/{id}/transcript")
    public ResponseEntity<TranscriptResponse> getTranscript(@PathVariable Long id) {
        TranscriptResponse transcript = transcriptService.getStudentTranscript(id);
        return ResponseEntity.ok(transcript);
    }
}