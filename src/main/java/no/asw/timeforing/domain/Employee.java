package no.asw.timeforing.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.Year;
import java.util.*;

@JsonPropertyOrder({"employeeId","billableHoursTotal", "revenuesPerYear"})
public class Employee {

    @Id
    private Long employeeId;
    private double billableHoursTotal;
    private double utilizationTotal;

    private Map<Integer, List<Revenue>> revenuePerYear = new HashMap<>();
    private Map<Integer, List<Project>> projectPerYear = new HashMap<>();

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

    public String toString() {
        return String.format("Employee[id=%s, billableHoursTotal=%s, revenuePerYear=%s, projectPerYear=%s]", employeeId, billableHoursTotal, revenuePerYear, projectPerYear);
    }

    public void addProject(Project project, Year year) {
        List<Project> projects = projectPerYear.get(year.getValue());
        if(projects == null){
            projects = new ArrayList<>();
            projectPerYear.put(year.getValue(), projects);
        }
        projects.add(project);
    }

    public void addRevenue(Revenue revenue, Year year) {
        List<Revenue> revenues = revenuePerYear.get(year.getValue());
        if(revenues == null){
            revenues = new ArrayList<>();
            revenuePerYear.put(year.getValue(), revenues);
        }
        revenues.add(revenue);
    }

    public Map<Integer, List<Revenue>> getRevenuePerYear() {
        return revenuePerYear;
    }

    public Map<Integer, List<Project>> getProjectPerYear() {
        return projectPerYear;
    }

    /*
        //TODO: kalkuler ved import?
        public Double getAverageUtilizationPercent(){
            return new BigDecimal(utilizationTotal/revenuePerMonth.size()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        //TODO:: lag comparator på klassenivå
        public List<Revenue> getRevenuePerMonth() {
            revenuePerMonth.sort(Comparator.comparing((Revenue revenue) -> revenue.getMonth().getValue()).reversed());
            return revenuePerMonth;
        }

        //TODO:: ta høyde for år
        public void addRevenue(Revenue revenue){
            this.revenuePerMonth.add(revenue);
            this.billableHoursTotal += revenue.getBillableHours();
            this.utilizationTotal += revenue.getUtilization();
        }
    */


}
