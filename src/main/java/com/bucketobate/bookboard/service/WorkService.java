package com.bucketobate.bookboard.service;

import com.bucketobate.bookboard.model.Author;
import com.bucketobate.bookboard.model.GeoType;
import com.bucketobate.bookboard.dto.WorkRequest;
import com.bucketobate.bookboard.model.Work;
import com.bucketobate.bookboard.exception.WorkNotFoundException;
import com.bucketobate.bookboard.model.WorkType;
import com.bucketobate.bookboard.repository.AuthorRepository;
import com.bucketobate.bookboard.repository.GeoTypeRepository;
import com.bucketobate.bookboard.repository.WorkRepository;
import com.bucketobate.bookboard.repository.WorkTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkService {

    private final WorkRepository workRepository;
    private final AuthorRepository authorRepository;
    private final GeoTypeRepository geoTypeRepository;
    private final WorkTypeRepository workTypeRepository;

    /*
    private final List<Book> books = new ArrayList<>(
            List.of(
                    new Book(1L, "The Blithedale Romance", "Nathaniel Hawthorne"),
                    new Book(2L, "The Marble Faun", "Nathaniel Hawthorne")
            )
    );
    */

    public WorkService(WorkRepository w, AuthorRepository a, GeoTypeRepository g, WorkTypeRepository wt) {
        this.workRepository = w;
        this.authorRepository = a;
        this.geoTypeRepository = g;
        this.workTypeRepository = wt;
    }

    private record ResolvedRefs(Author author, GeoType geoType, WorkType workType) {}

    private ResolvedRefs resolveRefs(WorkRequest request) {
        Author author = authorRepository.findByName(request.authorName())
                .orElseGet(() -> authorRepository.save(new Author(request.authorName())));

        GeoType geoType = geoTypeRepository.findById(request.geoTypeName())
                .orElseThrow(() -> new IllegalArgumentException("Unknown geo type: " + request.geoTypeName()));

        WorkType workType = workTypeRepository.findById(request.workTypeName())
                .orElseThrow(() -> new IllegalArgumentException("Unknown work type: " + request.workTypeName()));

        return new ResolvedRefs(author, geoType, workType);
    }

    public List<Work> findAll() {
        return workRepository.findAll();
    }

    public Work findById(Long id) {
        return workRepository.findById(id)
                .orElseThrow(() -> new WorkNotFoundException(id));
    }

    public Work create(WorkRequest request) {
        ResolvedRefs refs = resolveRefs(request);

        return workRepository.save(new Work(request.title(), refs.author(), refs.geoType(), refs.workType()));
    }

    @Transactional
    public Work update(Long id, WorkRequest request) {
        ResolvedRefs refs = resolveRefs(request);

        Work work = findById(id);
        work.setTitle(request.title());
        work.setAuthor(refs.author());
        work.setGeoType(refs.geoType());
        work.setWorkType(refs.workType());
        return workRepository.save(work);
    }

    public void delete(Long id) {
        if (!workRepository.existsById(id)) {
            throw new WorkNotFoundException(id);
        }
        workRepository.deleteById(id);
    }
}
