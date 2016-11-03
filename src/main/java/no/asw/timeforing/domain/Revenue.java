package no.asw.timeforing.domain;

import no.asw.timeforing.domain.csv.RevenueLine;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class Revenue {

    private Month month;
    private Double billableHours;
    private String revenue;
    private String prognosis;
    private Double utilization;

    public Revenue() { }

    public Revenue(RevenueLine revenueLineFromFile) {
        this.billableHours = revenueLineFromFile.getBillableHoursAsDouble();
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

    public Double getBillableHours() {
        return billableHours;
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
