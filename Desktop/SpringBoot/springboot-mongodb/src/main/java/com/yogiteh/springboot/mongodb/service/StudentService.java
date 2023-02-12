package com.yogiteh.springboot.mongodb.service;

import com.yogiteh.springboot.mongodb.models.Student;
import com.yogiteh.springboot.mongodb.models.StudentRequest;
import com.yogiteh.springboot.mongodb.models.StudentResponse;
import com.yogiteh.springboot.mongodb.models.Teacher;
import com.yogiteh.springboot.mongodb.repo.StudentRepo;
import com.yogiteh.springboot.mongodb.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherRepo teacherRepo;

    public void save(StudentRequest studentRequest) {
        Student studentData = new Student();
        studentData.setId(studentRequest.getId());
        studentData.setName(studentRequest.getName());


        List<Teacher> existTeacher = teacherRepo.findAll();
        List<String> existTeacherList = new ArrayList<>();
        existTeacher.forEach(teacher-> existTeacherList.add(teacher.getTeacher()));
        String[] split = studentRequest.getTeacherList().split(",");
        List<String> newTeacherList = Arrays.asList(split);
        List<Teacher> newTeacherAddInList = new ArrayList<>();
        if(existTeacherList.isEmpty()){
            newTeacherList.forEach(newTeacher->{
                Teacher teacher = new Teacher();
                teacher.setTeacher(newTeacher);
                teacherRepo.save(teacher);
                newTeacherAddInList.add(teacher);
            });
        }else {
            newTeacherList.forEach(newTeacher -> {
                if (!existTeacherList.contains(newTeacher)) {
                    Teacher teacher = new Teacher();
                    teacher.setTeacher(newTeacher);
                    teacherRepo.save(teacher);
                }
                Teacher teacherAddInList = new Teacher();
                teacherAddInList.setTeacher(newTeacher);
                newTeacherAddInList.add(teacherAddInList);

            });
        }
        studentData.setTeacherList(newTeacherAddInList);
        studentRepo.save(studentData);
    }

    public List<StudentResponse> findAll() {
        List<Student> allStudent = studentRepo.findAll();
        List<StudentResponse> studentResponseList = new ArrayList<>();

        allStudent.forEach(student->{
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setId(student.getId());
            studentResponse.setName(student.getName());
            String teacherListResponse = "";
            if(!StringUtils.isEmpty(student.getTeacherList().toString())){
                List<Teacher> teacherList = student.getTeacherList();
                teacherListResponse = teacherList.stream()
                        .map(teacher -> String.valueOf(teacher.getTeacher()))
                        .collect(Collectors.joining(", ", "", ""));

            }
            studentResponse.setTeacherList(teacherListResponse);
            studentResponseList.add(studentResponse);
        });
        return studentResponseList;
    }
    public Student edit(int id) {
        Optional<Student> byId = studentRepo.findById(id);
        Student student = byId.get();
        return student;
    }

    public void delete(int id) {
        studentRepo.deleteById(id);
    }
}
