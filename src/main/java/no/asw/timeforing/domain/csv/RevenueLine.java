package no.asw.timeforing.domain.csv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

@JsonPropertyOrder({"employeeId", "departmentId", "revenue", "prognosis", "discrepancy","discrepancyInPercent", "billableHours", "utilization" })
public class RevenueLine {

    /* Id	Avdeling    Omsetning	 Pro./Run-Ons 	Avvik	%-avvik	F-timer	Utf.grad */
    Long employeeId;
    String departmentId;
    String revenue;
    String prognosis;
    String discrepancy;
    String discrepancyInPercent;
    String billableHours;
    String utilization;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
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

    public String getDiscrepancy() {
        return discrepancy;
    }

    public void setDiscrepancy(String discrepancy) {
        this.discrepancy = discrepancy;
    }

    public String getDiscrepancyInPercent() {
        return discrepancyInPercent;
    }

    public void setDiscrepancyInPercent(String discrepancyInPercent) {
        this.discrepancyInPercent = discrepancyInPercent;
    }

    public String getBillableHours() {
        return billableHours;
    }

    @JsonIgnore
    public Double getBillableHoursAsDouble() {
        try {
            String hours = billableHours.replace(',', '.').trim();
            return Double.parseDouble(hours);
        } catch (Exception e) {
            return 0.;
        }
    }

    public void setBillableHours(String billableHours) {
        this.billableHours = billableHours;
    }

    public String getUtilization() {
        return utilization;
    }

    public void setUtilization(String utilization) {
        this.utilization = utilization;
    }

    public Double getUtilizationAsDouble() {
        try {
            String util = utilization.replace(',', '.').replace("%","").trim();
            return Double.parseDouble(util);
        } catch (Exception e) {
            return 0.;
        }
    }
}
