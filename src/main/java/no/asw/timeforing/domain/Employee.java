package no.asw.timeforing.domain;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Employee {

    @Id
    private Long employeeId;
    private double billableHours;
    private List<RevenueInformation> revenue;

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

    public String toString() {
        return String.format("Employee[id=%s, billableHours=%s, revenue=%s]", employeeId, billableHours, revenue);
    }

    public void addHours(String hours) {
        try {
            this.billableHours += Double.parseDouble(hours);
        } finally {
            return;
        }
    }

    public void addRevenueInformation(RevenueInformation revenueInformation){
        revenue.add(revenueInformation);
    }
}
