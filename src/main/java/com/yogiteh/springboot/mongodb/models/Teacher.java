package com.yogiteh.springboot.mongodb.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Teacher {
    private String teacher;
}
