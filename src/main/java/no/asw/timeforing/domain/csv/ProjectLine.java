package no.asw.timeforing.domain.csv;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Month;

@JsonPropertyOrder({"employeeId", "hours" })
public class ProjectLine {

    /* Id   F-timer */
    Long employeeId;
    String hours;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public double getHoursAsDouble(){
        try {
            String hours = getHours().replace(',', '.').trim();
            return Double.parseDouble(hours);
        } catch (Exception e) {
            return 0.;
        }
    }

}
