package com.yogiteh.springboot.mongodb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentResponse {

    private int id;
    private String name;

    private String teacherList;
}
