package com.bucketobate.bookboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "work_types")
public class WorkType {

    @Id
    @Column(name = "name", length = 20, updatable = false)
    private String name;

    @Column(name = "base_points", nullable = false, precision = 12, scale = 2)
    private BigDecimal basePoints;

    protected WorkType() {}
    public WorkType(String name, BigDecimal basePoints) {
        this.name = name;
        this.basePoints = basePoints;
    }

    public String getName() { return name; }
    public BigDecimal getBasePoints() { return basePoints; }
    public void setBasePoints(BigDecimal basePoints) { this.basePoints = basePoints; }

    @Override public boolean equals(Object o) {
        return o instanceof WorkType w && Objects.equals(getName(), w.getName());
    }
    @Override public int hashCode() { return Objects.hashCode(getName()); }
}
