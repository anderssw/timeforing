package no.asw.timeforing.domain;

import no.asw.timeforing.domain.csv.ProjectLine;
import no.asw.timeforing.domain.csv.RevenueLine;

import java.time.Month;

public class Revenue implements Comparable<Revenue>{

    private Month month;
    private Double customerBillableHours;
    private Double allBillableHours;
    private String revenue;
    private String prognosis;
    private Double utilization;

    public Revenue() { }

    public Revenue(RevenueLine revenueLineFromFile) {
        this.customerBillableHours = revenueLineFromFile.getBillableHoursAsDouble();
        this.revenue = revenueLineFromFile.getRevenue().trim();
        this.prognosis = revenueLineFromFile.getPrognosis().trim();
        this.utilization = revenueLineFromFile.getUtilizationAsDouble();
    }

    public Revenue(RevenueLine revenueLine, Month monthFromFileName) {
        this(revenueLine);
        this.month = monthFromFileName;
    }

    public Month getMonth() {
        return month;
    }

    public Double getCustomerBillableHours() {
        return customerBillableHours;
    }

    public String getRevenue() {
        return revenue;
    }

    public String getPrognosis() {
        return prognosis;
    }

    public Double getUtilization() {
        return utilization;
    }

    public Double getAllBillableHours() {
        return allBillableHours;
    }

    public void addProjectHours(ProjectLine project, Month month){
        if(this.month.equals(month)) {
            allBillableHours = project.getHoursAsDouble();
        }
    }

    @Override
    public String toString() {
        return "Revenue[" +
                "month=" + month +
                ", customerBillableHours=" + customerBillableHours +
                ", allBillableHours=" + allBillableHours +
                ", revenue=" + revenue +
                ", prognosis=" + prognosis +
                ", utilization='" + utilization + '\'' +
                ']';
    }

    @Override
    public int compareTo(Revenue other) {
        return other.getMonth().compareTo(getMonth());
    }
}
