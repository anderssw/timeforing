package no.asw.timeforing.domain;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Employee {

    @Id
    private Long employeeId;
    private double billableHoursTotal;
    private List<Revenue> revenuePerMonth = new ArrayList<>();

    public Employee(){}

    public Employee(Long employeeId, double billableHoursTotal) {
        this.employeeId = employeeId;
        this.billableHoursTotal = billableHoursTotal;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public double getBillableHoursTotal() {
        return billableHoursTotal;
    }

    public List<Revenue> getRevenuePerMonth() {
        revenuePerMonth.sort(Comparator.comparing((Revenue revenue) -> revenue.getMonth().getValue()).reversed());
        return revenuePerMonth;
    }

    public void addRevenue(Revenue revenue){
        this.revenuePerMonth.add(revenue);
        this.billableHoursTotal += revenue.getBillableHours();
    }

    public String toString() {
        return String.format("Employee[id=%s, billableHoursTotal=%s, revenuePerMonth=%s]", employeeId, billableHoursTotal, revenuePerMonth);
    }
}
