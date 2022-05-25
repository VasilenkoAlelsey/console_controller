package com.example.demo.repository;

import com.example.demo.entity.Department;
import com.example.demo.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findDepartmentByDepartmentName(String departmentName);

    List<Department> getAllByDepartmentNameIsContaining(String template);

    List<Department> getAllByHeadOfDepartmentNameContaining(String template);

    List<Department> findAll();

}
