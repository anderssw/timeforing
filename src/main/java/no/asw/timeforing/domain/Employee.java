package no.asw.timeforing.domain;

import org.springframework.data.annotation.Id;

public class Employee {

    @Id
    private Long employeeId;
    private int hours;

    public Employee(){}

    public Employee(Long employeeId, int hours) {
        this.employeeId = employeeId;
        this.hours = hours;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public int getHours() {
        return hours;
    }

    public String toString() {
        return String.format("Employee[id=%s, hours=%s]", employeeId, hours);
    }
}
