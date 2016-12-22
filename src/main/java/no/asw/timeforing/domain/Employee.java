package no.asw.timeforing.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import no.asw.timeforing.domain.csv.ProjectLine;
import org.springframework.data.annotation.Id;

import java.time.Month;
import java.time.Year;
import java.util.*;

@JsonPropertyOrder({"employeeId","aggregatesForCurrentYear", "revenuesForCurrentYear"})
public class Employee {

    @Id
    private Long employeeId;

    private NavigableMap<Integer, SortedSet<Revenue>> revenuePerYear = new TreeMap<>();
    private NavigableMap<Integer, Aggregates> aggregatesPerYear = new TreeMap<>();

    public Employee() {}

    public Employee(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
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
        revenues.forEach(revenue -> {
            if (revenue.getMonth().equals(month)) {
                revenue.addProjectHours(projectLine);
                addAggregates(revenue, year);
            }
        });
    }

    private void addAggregates(Revenue revenue, Year year) {
        Aggregates aggregates = aggregatesPerYear.get(year.getValue());
        if(aggregates == null) {
            aggregates = new Aggregates();
            aggregatesPerYear.put(year.getValue(), aggregates);
        }
        aggregates.aggregateInfoFrom(revenue);
    }

    public Map<Integer, SortedSet<Revenue>> getRevenuePerYear() {
        return revenuePerYear;
    }

    public SortedSet<Revenue> getRevenuesForCurrentYear() {
        if(revenuePerYear.lastEntry() == null) return new TreeSet<>();
        return revenuePerYear.lastEntry().getValue();
    }

    public NavigableMap<Integer, Aggregates> getAggregatesPerYear() {
        return aggregatesPerYear;
    }

    public Aggregates getAggregatesForCurrentYear(){
        if(aggregatesPerYear.lastEntry() == null) return new Aggregates();
        return aggregatesPerYear.lastEntry().getValue();
    }

    public String toString() {
        return String.format("Employee[id=%s, revenuePerYear=%s, aggregatesPerYear=%s]", employeeId, revenuePerYear, aggregatesPerYear);
    }

}
