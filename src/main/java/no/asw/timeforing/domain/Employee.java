package no.asw.timeforing.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import no.asw.timeforing.domain.csv.ProjectLine;
import org.springframework.data.annotation.Id;

import java.time.Month;
import java.time.Year;
import java.util.*;

@JsonPropertyOrder({"employeeId","billableHoursTotal", "revenuesPerYear"})
public class Employee {

    @Id
    private Long employeeId;
    private double billableHoursTotal;
    private double utilizationTotal;

    private NavigableMap<Integer, SortedSet<Revenue>> revenuePerYear = new TreeMap<>();

    public Employee() {}

    public Employee(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public double getBillableHoursTotal() {
        return billableHoursTotal;
    }

    public String toString() {
        return String.format("Employee[id=%s, billableHoursTotal=%s, revenuePerYear=%s]", employeeId, billableHoursTotal, revenuePerYear);
    }

    public void addRevenue(Revenue revenue, Year year) {
        SortedSet<Revenue> revenues = revenuePerYear.get(year.getValue());
        if(revenues == null){
            revenues = new TreeSet<>();
            revenuePerYear.put(year.getValue(), revenues);
        }
        revenues.add(revenue);
    }

    public void addProjectHours(ProjectLine projectLine, Year year, Month month) {
        SortedSet<Revenue> revenues = revenuePerYear.get(year.getValue());
        if(revenues == null){ return; }
        revenues.forEach(revenue -> revenue.addProjectHours(projectLine, month));
    }

    public Map<Integer, SortedSet<Revenue>> getRevenuePerYear() {
        return revenuePerYear;
    }

    public SortedSet<Revenue> getRevenuesForCurrentYear() {
        if(revenuePerYear.lastEntry() == null) return new TreeSet<>();
        return revenuePerYear.lastEntry().getValue();
    }


    /*
        //TODO: kalkuler ved import?
        public Double getAverageUtilizationPercent(){
            return new BigDecimal(utilizationTotal/revenuePerMonth.size()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

    */

}
