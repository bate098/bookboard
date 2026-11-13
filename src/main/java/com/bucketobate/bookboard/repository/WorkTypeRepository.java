package com.bucketobate.bookboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bucketobate.bookboard.model.WorkType;

public interface WorkTypeRepository extends JpaRepository<WorkType, String>{
}
