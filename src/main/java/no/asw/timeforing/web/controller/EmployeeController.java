package no.asw.timeforing.web.controller;

import no.asw.timeforing.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


}
