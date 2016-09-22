package no.asw.timeforing.domain;


public class Employee {

    private String employeeId;
    private int hours;

    public Employee(String employeeId, int hours) {
        this.employeeId = employeeId;
        this.hours = hours;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public int getHours() {
        return hours;
    }
}
