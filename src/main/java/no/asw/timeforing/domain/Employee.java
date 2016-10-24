package no.asw.timeforing.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@JsonPropertyOrder({"employeeId","billableHoursTotal","averageUtilizationPercent", "revenuePerMonth"})
public class Employee {

    @Id
    private Long employeeId;
    private double billableHoursTotal;
    private double utilizationTotal;
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

    public Double getAverageUtilizationPercent(){
        return new BigDecimal(utilizationTotal/revenuePerMonth.size()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public List<Revenue> getRevenuePerMonth() {
        revenuePerMonth.sort(Comparator.comparing((Revenue revenue) -> revenue.getMonth().getValue()).reversed());
        return revenuePerMonth;
    }

    public void addRevenue(Revenue revenue){
        this.revenuePerMonth.add(revenue);
        this.billableHoursTotal += revenue.getBillableHours();
        this.utilizationTotal += revenue.getUtilization();
    }

    public String toString() {
        return String.format("Employee[id=%s, billableHoursTotal=%s, revenuePerMonth=%s]", employeeId, billableHoursTotal, revenuePerMonth);
    }
}
