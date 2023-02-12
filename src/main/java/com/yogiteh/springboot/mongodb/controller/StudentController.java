package com.yogiteh.springboot.mongodb.controller;

import com.yogiteh.springboot.mongodb.models.Student;
import com.yogiteh.springboot.mongodb.models.StudentRequest;
import com.yogiteh.springboot.mongodb.models.StudentResponse;
import com.yogiteh.springboot.mongodb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String home(Model model){
        List<StudentResponse> all = studentService.findAll();
        model.addAttribute("all_students",all);
        return "index";
    }

    @GetMapping("/load_form")
    public String loadForm(){
        return "add";
    }
    @GetMapping("/edit_form/{id}")
    public String viewForm(@PathVariable (value = "id") int id, Model model){
        Student edit = studentService.edit(id);
        model.addAttribute("student",edit);
        return "edit";
    }
    @PostMapping("/save")
    public String addStudent(@ModelAttribute StudentRequest studentRequest, HttpSession session){
        this.studentService.save(studentRequest);
        session.setAttribute("msg", "Student details save...");
        return "redirect:/load_form";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute StudentRequest studentRequest, HttpSession session){
        this.studentService.save(studentRequest);
        session.setAttribute("msg", "Student details update...");
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, HttpSession session){
        studentService.delete(id);
        session.setAttribute("msg", "Student details delete...");
        return "redirect:/";
    }
}
