package no.asw.timeforing.domain;

import no.asw.timeforing.domain.csv.RevenueLine;

import java.time.Month;

public class Revenue {

    private Month month;
    private Double billableHours;
    private String revenue;
    private String prognosis;
    private String utilization;

    public Revenue(RevenueLine revenueLineFromFile) {
        this.billableHours = revenueLineFromFile.getBillableHoursAsDouble();
        this.revenue = revenueLineFromFile.getRevenue().trim();
        this.prognosis = revenueLineFromFile.getRevenue().trim();
        this.utilization = revenueLineFromFile.getUtilization();
    }

    public Revenue(RevenueLine revenueLine, Month monthFromFileName) {
        this(revenueLine);
        this.month = monthFromFileName;
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

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getPrognosis() {
        return prognosis;
    }

    public void setPrognosis(String prognosis) {
        this.prognosis = prognosis;
    }

    public String getUtilization() {
        return utilization;
    }

    public void setUtilization(String utilization) {
        this.utilization = utilization;
    }

    @Override
    public String toString() {
        return "Revenue[" +
                "month=" + month +
                ", billableHours=" + billableHours +
                ", revenue=" + revenue +
                ", prognosis=" + prognosis +
                ", utilization='" + utilization + '\'' +
                ']';
    }
}
