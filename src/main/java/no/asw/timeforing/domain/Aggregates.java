package no.asw.timeforing.domain;

import java.math.BigDecimal;

public class Aggregates {

    private Double allBillableHoursYear = 0.;
    private Double customerBillableHoursYear = 0.;
    private Double totalRevenue = 0.;
    private Double totalUtilization = 0.;
    private int monthsImported = 0;

    public Double getAllBillableHoursYear() {
        return allBillableHoursYear;
    }

    public Double getCustomerBillableHoursYear() {
        return customerBillableHoursYear;
    }

    public Double getAverageUtilization() {
        return new BigDecimal(totalUtilization/monthsImported).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void aggregateInfoFrom(Revenue revenue) {
        allBillableHoursYear += revenue.getAllBillableHours();
        customerBillableHoursYear += revenue.getCustomerBillableHours();
        totalUtilization += revenue.getUtilization();
        totalRevenue += revenue.getRevenueAsDouble();
        monthsImported++;
    }
}
