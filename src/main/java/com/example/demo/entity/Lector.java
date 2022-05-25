package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lectors")
public class Lector {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "degree")
    private String lectorsDegree;

    @Column(name = "salary")
    private int salary;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "lector_department",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private List<Department> departments;

    public void addDepartmentToLector(Department department) {
        if (departments == null) departments = new ArrayList<>();
        departments.add(department);
    }

    public Lector() {
    }

    public Lector(String fullName, String lectorsDegree, int salary) {
        this.fullName = fullName;
        this.lectorsDegree = lectorsDegree;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLectorsDegree() {
        return lectorsDegree;
    }

    public void setLectorsDegree(String lectorsDegree) {
        this.lectorsDegree = lectorsDegree;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", lectorsDegree='" + lectorsDegree + '\'' +
                ", salary=" + salary +
                '}';
    }
}
