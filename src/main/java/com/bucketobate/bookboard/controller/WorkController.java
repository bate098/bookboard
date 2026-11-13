package com.bucketobate.bookboard.controller;

import com.bucketobate.bookboard.model.Work;
import com.bucketobate.bookboard.dto.WorkRequest;
import com.bucketobate.bookboard.service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class WorkController {

    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping
    public List<Work> getAll() {
        return workService.findAll();
    }

    @GetMapping("/{id}")
    public Work getById(@PathVariable Long id) {
        return workService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Work create(@RequestBody WorkRequest request) {
        return workService.create(request);
    }

    @PutMapping("/{id}")
    public Work update(@PathVariable Long id, @RequestBody WorkRequest request) {
        return workService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        workService.delete(id);
    }

}
