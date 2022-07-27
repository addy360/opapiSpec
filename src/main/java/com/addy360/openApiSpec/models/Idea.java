package com.addy360.openApiSpec.models;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Table(name = "ideas")
@Entity
@Data
public class Idea {
    @Id
    @GeneratedValue
    long id;
    String title;

    @Column(length = 1000)
    String description;

    @CreationTimestamp
    Date createdAt;
    @UpdateTimestamp
    Date updatedAt;
}
