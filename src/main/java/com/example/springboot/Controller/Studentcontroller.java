package com.example.springboot.Controller;

import com.example.springboot.Entity.Student;
import com.example.springboot.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Studentcontroller {

    @Autowired
StudentRepository studentRepository;
    @PostMapping("/api/students")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
    }
    @GetMapping("/api/students")
public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentRepository.findAll(),HttpStatus.OK);
}
    @GetMapping("/api/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/api/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id, @RequestBody Student stud) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentOptional.get().setStudentName(stud.getStudentName());
            studentOptional.get().setStudentEmail(stud.getStudentEmail());
            studentOptional.get().setStudentAddress(stud.getStudentAddress());
            return new ResponseEntity<>(studentRepository.save(studentOptional.get()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/api/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
