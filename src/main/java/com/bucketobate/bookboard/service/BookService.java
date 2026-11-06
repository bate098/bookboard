package com.bucketobate.bookboard.service;

import com.bucketobate.bookboard.exception.BookNotFoundException;
import com.bucketobate.bookboard.model.Book;
import com.bucketobate.bookboard.model.BookRequest;
import com.bucketobate.bookboard.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    /*
    private final List<Book> books = new ArrayList<>(
            List.of(
                    new Book(1L, "The Blithedale Romance", "Nathaniel Hawthorne"),
                    new Book(2L, "The Marble Faun", "Nathaniel Hawthorne")
            )
    );
    */

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book create(BookRequest request) {
        return bookRepository.save(new Book(request.title(), request.author()));
    }

    @Transactional
    public Book update(Long id, BookRequest request) {
        Book book = findById(id);
        book.setTitle(request.title());
        book.setAuthor(request.author());
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
    }
}
