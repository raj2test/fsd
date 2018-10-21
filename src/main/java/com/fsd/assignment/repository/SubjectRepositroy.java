package com.fsd.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsd.assignment.entity.Subject;

@Repository
public interface SubjectRepositroy extends JpaRepository<Subject, Long> {

}
