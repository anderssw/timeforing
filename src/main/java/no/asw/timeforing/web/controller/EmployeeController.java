package no.asw.timeforing.web.controller;

import no.asw.timeforing.domain.Project;
import no.asw.timeforing.domain.Revenue;
import no.asw.timeforing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value ="/{employeeId}/revenue/{year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Revenue> getRevenueForYear(@PathVariable Long employeeId, @PathVariable int year){
        return employeeService.getRevenueForYear(employeeId, year);
    }

    @RequestMapping(value ="/{employeeId}/project/{year}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Project> getProjectForYear(@PathVariable Long employeeId, @PathVariable int year){
        return employeeService.getProjectForYear(employeeId, year);
    }
}
