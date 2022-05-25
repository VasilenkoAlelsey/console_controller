package com.example.demo.service;

import com.example.demo.entity.Department;

import java.util.List;
import java.util.Set;

public interface DepartmentService {

    void getHeadOfDepartmentNameByDepartmentName(String userCommand);

    void countLectorsInDepartmentByDegree(String userCommand);

    void getAverageSalaryOfDepartment(String userCommand);

    void countLectorsInDepartment(String userCommand);

    void globalSearchByTemplate(String userCommand);

}
