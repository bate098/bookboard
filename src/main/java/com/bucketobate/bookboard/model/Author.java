package com.bucketobate.bookboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Objects;

@Entity
@Table(name = "authors",  uniqueConstraints = @UniqueConstraint(name = "uq_authors_name", columnNames = "name"))
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    protected Author() {}
    public Author(String name) {this.name = name;}

    public String getName() { return name; }

    @Override public boolean equals(Object o) {
        return o instanceof Author a && Objects.equals(getName(), a.getName());
    }
    @Override public int hashCode() { return Objects.hashCode(getName()); }
}
