package com.educationportal.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by aleksandr on 13.02.2016.
 */
@Entity
@Table(name = "document", indexes = {
        @Index(columnList = "uniqueNumber", name = "UniqueNumber_index")})
public class DocumentEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column()
    private String title;

    @NotEmpty
    @Column(unique = true)
    private String uniqueNumber;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private PersonEntity person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
