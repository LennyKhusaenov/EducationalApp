package com.educationportal.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * Created by leniz on 16.05.2016.
 */
@Entity
@Table(name = "person")
public class PersonEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column()
    private String name;

    @Column()
    private String surname;

    @Column()
    private String email;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = DocumentEntity.class)
    @LazyCollection(LazyCollectionOption.TRUE)
    private List<DocumentEntity> documents = new Vector<DocumentEntity>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DocumentEntity> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentEntity> documents) {
        this.documents = documents;
    }
}
