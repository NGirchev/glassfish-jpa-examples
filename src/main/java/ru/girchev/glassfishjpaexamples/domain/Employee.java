package ru.girchev.glassfishjpaexamples.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(schema = "chapter6")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Department department;

    @OneToOne
    private ParkingSpace parkingSpace;

    @ManyToMany
    @JoinTable(schema = "chapter6")
    private List<Document> documents = new ArrayList<Document>();

    @ManyToMany
    @JoinTable(schema = "chapter6")
    private List<Project> projects = new ArrayList<Project>();
}
