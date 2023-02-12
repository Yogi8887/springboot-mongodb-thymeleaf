package com.yogiteh.springboot.mongodb.repo;

import com.yogiteh.springboot.mongodb.models.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends MongoRepository<Teacher, Integer> {

}
