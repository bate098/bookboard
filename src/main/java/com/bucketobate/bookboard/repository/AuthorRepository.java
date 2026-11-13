package com.bucketobate.bookboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bucketobate.bookboard.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
