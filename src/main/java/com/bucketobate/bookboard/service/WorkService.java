package com.bucketobate.bookboard.service;

import com.bucketobate.bookboard.model.WorkRequest;
import com.bucketobate.bookboard.model.Work;
import com.bucketobate.bookboard.exception.WorkNotFoundException;
import com.bucketobate.bookboard.repository.WorkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkService {

    private final WorkRepository workRepository;

    /*
    private final List<Book> books = new ArrayList<>(
            List.of(
                    new Book(1L, "The Blithedale Romance", "Nathaniel Hawthorne"),
                    new Book(2L, "The Marble Faun", "Nathaniel Hawthorne")
            )
    );
    */

    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public List<Work> findAll() {
        return workRepository.findAll();
    }

    public Work findById(Long id) {
        return workRepository.findById(id)
                .orElseThrow(() -> new WorkNotFoundException(id));
    }

    public Work create(WorkRequest request) {
        return workRepository.save(new Work(request.title(), request.author()));
    }

    @Transactional
    public Work update(Long id, WorkRequest request) {
        Work work = findById(id);
        work.setTitle(request.title());
        work.setAuthor(request.author());
        return workRepository.save(work);
    }

    public void delete(Long id) {
        if (!workRepository.existsById(id)) {
            throw new WorkNotFoundException(id);
        }
        workRepository.deleteById(id);
    }
}
