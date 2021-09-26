package com.springrest.springrest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
