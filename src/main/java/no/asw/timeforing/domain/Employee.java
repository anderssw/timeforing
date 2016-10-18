package no.asw.timeforing.domain;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    @Id
    private Long employeeId;
    private double billableHours;
    private List<Revenue> revenues = new ArrayList<>();

    public Employee(){}

    public Employee(Long employeeId, double billableHours) {
        this.employeeId = employeeId;
        this.billableHours = billableHours;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public double getBillableHours() {
        return billableHours;
    }

    public List<Revenue> getRevenues() {
        return revenues;
    }

    public String toString() {
        return String.format("Employee[id=%s, billableHours=%s, revenues=%s]", employeeId, billableHours, revenues);
    }

    public void addRevenue(Revenue revenue){
        this.revenues.add(revenue);
        this.billableHours += revenue.getBillableHours();
    }
}
