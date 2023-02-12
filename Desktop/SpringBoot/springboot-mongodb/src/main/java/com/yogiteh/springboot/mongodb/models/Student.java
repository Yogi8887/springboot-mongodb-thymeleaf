package com.yogiteh.springboot.mongodb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "students")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    @Id
    private int id;
    private String name;

    private List<Teacher> teacherList;
}

