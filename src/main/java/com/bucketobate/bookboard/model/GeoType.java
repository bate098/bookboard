package com.bucketobate.bookboard.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "geo_types")
public class GeoType {

    @Id
    @Column(name = "name", length = 20, updatable = false)
    private String name;

    protected GeoType() {}
    public GeoType(String name) { this.name = name; }

    public String getName() { return name; }

    @Override public boolean equals(Object o) {
        return o instanceof GeoType g && Objects.equals(getName(), g.getName());
    }
    @Override public int hashCode() { return Objects.hashCode(getName()); }
}
