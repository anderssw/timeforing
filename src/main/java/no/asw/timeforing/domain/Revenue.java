package no.asw.timeforing.domain;

import no.asw.timeforing.domain.csv.ProjectLine;
import no.asw.timeforing.domain.csv.RevenueLine;

import java.time.Month;

public class Revenue implements Comparable<Revenue>{

    private Month month;
    private double customerBillableHours;
    private double allBillableHours;
    private String revenue;
    private String prognosis;
    private double utilization;

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

    public double getCustomerBillableHours() {
        return customerBillableHours;
    }

    public String getRevenue() {
        return revenue;
    }

    public String getPrognosis() {
        return prognosis;
    }

    public double getUtilization() {
        return utilization;
    }

    public double getAllBillableHours() {
        return allBillableHours;
    }

    public void addProjectHours(ProjectLine project){
        allBillableHours = project.getHoursAsDouble();
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
