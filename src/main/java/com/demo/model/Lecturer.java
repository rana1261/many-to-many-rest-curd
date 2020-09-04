package com.demo.model;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lecturers")
public class Lecturer extends Person{
	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, columnDefinition = "bigint(20)")
    private  BigInteger salary;
    
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "lecturer_students",
            joinColumns = {@JoinColumn(name = "lecturer_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Set<Student> students = new HashSet<>();
    
    // CONSTRUCTORS
    
    public Lecturer() { }

    public Lecturer(String firstName, String lastName, BigInteger salary) {
        super(firstName, lastName);
        this.salary = salary;
    }
    
    // GETTERS AND SETTERS

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public BigInteger getSalary() {return salary;}
    public void setSalary(BigInteger salary) {this.salary = salary;}

    public Set<Student> getStudents() {return students;}
    public void setStudents(Set<Student> students) {this.students = students;}

}
