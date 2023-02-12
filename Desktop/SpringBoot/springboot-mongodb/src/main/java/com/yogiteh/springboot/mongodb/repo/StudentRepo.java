package com.yogiteh.springboot.mongodb.repo;

import com.yogiteh.springboot.mongodb.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student, Integer> {
}
