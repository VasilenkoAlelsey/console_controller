package com.example.demo.controller;

import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class ConsoleController {

    private final DepartmentService cityService;

    public ConsoleController(DepartmentService departmentService) {
        this.cityService = departmentService;

        Scanner scanner = new Scanner(System.in);
        String userCommand;
        System.out.println("Enter your command:");

        while (!(userCommand = scanner.nextLine()).equalsIgnoreCase("exit")) {
            if (userCommand.startsWith("Who is head of department")) {
                departmentService.getHeadOfDepartmentNameByDepartmentName(userCommand);
            }

            else if (userCommand.startsWith("Show") && userCommand.endsWith("statistics")) {
                departmentService.countLectorsInDepartmentByDegree(userCommand);
            }

            else if (userCommand.startsWith("Show the average salary for the department")) {
                departmentService.getAverageSalaryOfDepartment(userCommand);
            }

            else if (userCommand.startsWith("Show count of employee for")) {
                departmentService.countLectorsInDepartment(userCommand);
            }

            else if (userCommand.startsWith("Global search by")) {
                departmentService.globalSearchByTemplate(userCommand);
            }

            else {
                System.out.println("Please enter correct command");
            }
        }
        scanner.close();
    }
}
