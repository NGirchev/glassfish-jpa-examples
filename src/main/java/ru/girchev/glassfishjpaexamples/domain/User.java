package ru.girchev.glassfishjpaexamples.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Girchev N.A.
 * Date: 11.02.2019
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Table(schema = "chapter6")
//@NamedQuery(name = "User.getAll", query = "SELECT u from User u")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_ID")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private int age;
}
