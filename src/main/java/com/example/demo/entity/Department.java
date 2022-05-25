package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "department_head")
    private String headOfDepartmentName;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "lector_department",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "lector_id")
    )
    private List<Lector> lectors;

    public void addLectorsToDepartment(Lector lector) {
        if (lectors == null) lectors = new ArrayList<>();
        lectors.add(lector);
    }

    public Department() {
    }

    public Department(String departmentName, String headOfDepartmentName) {
        this.departmentName = departmentName;
        this.headOfDepartmentName = headOfDepartmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHeadOfDepartmentName() {
        return headOfDepartmentName;
    }

    public void setHeadOfDepartmentName(String headOfDepartmentName) {
        this.headOfDepartmentName = headOfDepartmentName;
    }

    public List<Lector> getLectors() {
        return lectors;
    }

    public void setLectors(List<Lector> lectors) {
        this.lectors = lectors;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", headOfDepartmentName='" + headOfDepartmentName + '\'' +
                '}';
    }
}
