package com.yogiteh.springboot.mongodb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentRequest {
    private int id;
    private String name;

    private String teacherList;
}
