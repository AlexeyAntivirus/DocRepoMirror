package com.revex.docrepo.controllers;

import com.revex.docrepo.exchange.student.GetAllStudentsResponsePayload;
import com.revex.docrepo.exchange.student.InsertNewStudentRequestPayload;
import com.revex.docrepo.exchange.student.InsertNewStudentResponsePayload;
import com.revex.docrepo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping("/all")
    public GetAllStudentsResponsePayload getAllStudents() {
        return this.service.getAllStudents();
    }

    @ResponseBody
    @PutMapping("/insert")
    public InsertNewStudentResponsePayload insertNewStudent(@RequestBody InsertNewStudentRequestPayload payload) {
        return this.service.insertNewStudent(payload);
    }
}
