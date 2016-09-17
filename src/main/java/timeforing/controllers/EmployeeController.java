package timeforing.controllers;

import timeforing.domain.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @RequestMapping("/employee")
    public Employee getEmployee(@RequestParam int employeeId){
        return new Employee("3145", 23);
    }

}
