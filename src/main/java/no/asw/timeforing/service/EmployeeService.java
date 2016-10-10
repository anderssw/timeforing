package no.asw.timeforing.service;

import no.asw.timeforing.domain.Employee;
import no.asw.timeforing.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployee(Long id) {
        return employeeRepository.findOne(id);
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

}
