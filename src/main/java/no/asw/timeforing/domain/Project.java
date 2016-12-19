package no.asw.timeforing.domain;

import no.asw.timeforing.domain.csv.ProjectLine;
import no.asw.timeforing.domain.csv.RevenueLine;

import java.time.Month;

public class Project {

    private Month month;
    private double hours;

    public Project() { }

    public Project(ProjectLine projectLine) {
        this.hours = projectLine.getHoursAsDouble();
    }

    public Project(ProjectLine projectLine, Month monthFromFileName) {
        this(projectLine);
        this.month = monthFromFileName;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Project[" +
                "month=" + month +
                "hours=" + hours +
                ']';
    }
}
