package com.bucketobate.bookboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bucketobate.bookboard.model.GeoType;

public interface GeoTypeRepository extends JpaRepository<GeoType, String> {
}