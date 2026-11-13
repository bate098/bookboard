CREATE TABLE geo_types (
                        name VARCHAR(20) PRIMARY KEY
);

CREATE TABLE work_types (
                        name        VARCHAR(20) PRIMARY KEY,
                        base_points NUMERIC(12, 2) NOT NULL CHECK (base_points >= 0)
);

CREATE TABLE authors (
                        id    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                        name  TEXT NOT NULL,
                        CONSTRAINT uq_authors_name UNIQUE (name)
);

CREATE SEQUENCE works_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE works (
                        id          BIGINT PRIMARY KEY DEFAULT nextval('works_seq'),
                        title       TEXT NOT NULL,
                        author_id   BIGINT NOT NULL REFERENCES authors(id) ON DELETE RESTRICT,
                        geo_type    VARCHAR(20) NOT NULL REFERENCES geo_types(name) ON UPDATE CASCADE,
                        work_type   VARCHAR(20) NOT NULL REFERENCES work_types(name) ON UPDATE CASCADE
);

CREATE INDEX idx_works_author_id ON works(author_id);