package com.educationportal.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Tables;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

@Entity
@Table(name ="program")
public class EducationalProgramEntity {
    @Id
    @GeneratedValue
    private long id;

    @Column()
    private String name;

    @Column()
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
