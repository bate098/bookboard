package com.bucketobate.bookboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bucketobate.bookboard.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
