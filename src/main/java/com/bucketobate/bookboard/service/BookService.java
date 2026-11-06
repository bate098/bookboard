package com.bucketobate.bookboard.service;

import com.bucketobate.bookboard.exception.BookNotFoundException;
import com.bucketobate.bookboard.model.Book;
import com.bucketobate.bookboard.model.BookRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>(
            List.of(
                    new Book(1L, "The Blithedale Romance", "Nathaniel Hawthorne"),
                    new Book(2L, "The Marble Faun", "Nathaniel Hawthorne")
            )
    );

    private final AtomicLong idSequence = new AtomicLong(2);

    public List<Book> findAll() {
        return books;
    }

    public Book findById(Long id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book create(BookRequest request) {
        Book book = new Book(idSequence.incrementAndGet(), request.title(), request.author());
        books.add(book);
        return book;
    }

    public Book update(Long id, BookRequest request) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).id().equals(id)) {
                Book replacement = new Book(id, request.title(), request.author());
                books.set(i, replacement);
                return replacement;
            }
        }
        throw new BookNotFoundException(id);
    }

    public void delete(Long id) {
        boolean removed = books.removeIf(book -> book.id().equals(id));
        if (!removed) {
            throw new BookNotFoundException(id);
        }
    }
}
