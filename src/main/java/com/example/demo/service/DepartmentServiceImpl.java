package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Lector;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Override
    public void getHeadOfDepartmentNameByDepartmentName(String userCommand) {
        String departmentName = checkEnterLine(userCommand);
        String headOfDepartmentName = repository
                .findDepartmentByDepartmentName(departmentName)
                .getHeadOfDepartmentName();

        System.out.printf("Head of %s department is %s\n", departmentName, headOfDepartmentName);
    }

    @Override
    public void countLectorsInDepartmentByDegree(String userCommand) {
        int indexOfFirstBlank = userCommand.indexOf(" ");
        int indexOfSecondBlank = userCommand.lastIndexOf(" ");
        String departmentName = userCommand.substring(indexOfFirstBlank + 1, indexOfSecondBlank);

        System.out.println(
                "assistans - " + countLectorsByDegree(departmentName, "assistans") + ". \n" +
                        "associate professors - " + countLectorsByDegree(departmentName, "associate professors") + "\n" +
                        "professors - " + countLectorsByDegree(departmentName, "professors") + "\n");
    }

    @Override
    public void getAverageSalaryOfDepartment(String userCommand) {
        String departmentName = checkEnterLine(userCommand);

        double count = repository.findDepartmentByDepartmentName(departmentName)
                .getLectors()
                .stream()
                .mapToInt(Lector::getSalary)
                .average()
                .getAsDouble();

        System.out.printf("The average salary of %s is %f", departmentName, count);
    }

    @Override
    public void countLectorsInDepartment(String userCommand) {
        String departmentName = checkEnterLine(userCommand);

        int count = repository
                .findDepartmentByDepartmentName(departmentName)
                .getLectors()
                .size();

        System.out.println(count);
    }

    @Override
    public void globalSearchByTemplate(String userCommand) {
        String template = checkEnterLine(userCommand);

        List<Lector> listOfFoundLecturers = new ArrayList<>();
        repository.findAll()
                .stream()
                .map(Department::getLectors)
                .forEach(listOfFoundLecturers::addAll);

        List<String> resultList = repository
                .getAllByDepartmentNameIsContaining(template)
                .stream()
                .filter(x -> x.getDepartmentName().contains(template))
                .map(Department::getDepartmentName)
                .collect(Collectors.toList());

        resultList.addAll(repository
                .getAllByHeadOfDepartmentNameContaining(template)
                .stream()
                .filter(x -> x.getHeadOfDepartmentName().contains(template))
                .map(Department::getHeadOfDepartmentName)
                .collect(Collectors.toList()));

        resultList.addAll(listOfFoundLecturers.stream()
                .filter(x -> x.getFullName().contains(template))
                .map(Lector::getFullName)
                .collect(Collectors.toList()));

        System.out.println(resultList);
    }

    private String checkEnterLine(String userCommand) {
        Set<String> listOfDepartmentNames = repository
                .findAll()
                .stream()
                .map(Department::getDepartmentName)
                .collect(Collectors.toSet());

        int indexOfBlank = userCommand.lastIndexOf(" ");
        String departmentName = userCommand.substring(indexOfBlank + 1);

        if (!listOfDepartmentNames.contains(departmentName)) {
            System.out.printf("Department with name %s is not exist", departmentName);
            return null;
        } else {
            return departmentName;
        }
    }

    private Long countLectorsByDegree(String departmentName, String degree) {
        return repository
                .findDepartmentByDepartmentName(departmentName)
                .getLectors()
                .stream()
                .filter(a -> a.getLectorsDegree().equals(degree))
                .count();
    }
}
