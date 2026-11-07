package com.bucketobate.bookboard.exception;

public class WorkNotFoundException extends RuntimeException {
    public WorkNotFoundException(Long id) {
        super("Work not found with id " + id);
    }
}
