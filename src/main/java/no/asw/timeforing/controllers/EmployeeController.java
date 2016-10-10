package no.asw.timeforing.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import no.asw.timeforing.domain.Employee;

@RestController
public class EmployeeController {

    @RequestMapping("/employee")
    public Employee getEmployee(){
        return new Employee("3145", 23);
    }

}
