package no.asw.timeforing.service;


import no.asw.timeforing.domain.Employee;
import no.asw.timeforing.domain.Project;
import no.asw.timeforing.domain.Revenue;
import no.asw.timeforing.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Revenue> getRevenueForYear(Long employeeId, int year){
        Employee employee = employeeRepository.findOne(employeeId);
        List<Revenue> revenue = employee.getRevenuePerYear().get(year);
        return revenue == null ? new ArrayList<>() : revenue;

    }


    public List<Project> getProjectForYear(Long employeeId, int year) {
        Employee employee = employeeRepository.findOne(employeeId);
        List<Project> project = employee.getProjectPerYear().get(year);
        return project == null ? new ArrayList<>() : project;
    }
}
