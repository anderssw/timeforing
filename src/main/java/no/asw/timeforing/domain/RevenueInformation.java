package no.asw.timeforing.domain;

import java.time.Month;

public class RevenueInformation {

    private Month month;
    private Double billableHours;
    private Integer revenue;
    private Integer prognosis;
    private String utilization;

    public RevenueInformation(Revenue revenueFromFile) {
        this.billableHours = revenueFromFile.getBillableHoursAsDouble();
        this.revenue = Integer.parseInt(revenueFromFile.getRevenue());
        this.prognosis = Integer.parseInt(revenueFromFile.getRevenue());
        this.utilization = revenueFromFile.getUtilization();
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Double getBillableHours() {
        return billableHours;
    }

    public void setBillableHours(Double billableHours) {
        this.billableHours = billableHours;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(Integer prognosis) {
        this.prognosis = prognosis;
    }

    public String getUtilization() {
        return utilization;
    }

    public void setUtilization(String utilization) {
        this.utilization = utilization;
    }
}
