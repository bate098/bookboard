package com.bucketobate.bookboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bucketobate.bookboard.model.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {
}
