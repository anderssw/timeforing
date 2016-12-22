package no.asw.timeforing.service.csv;

import no.asw.timeforing.domain.Employee;
import no.asw.timeforing.domain.Revenue;
import no.asw.timeforing.domain.csv.RevenueLine;
import no.asw.timeforing.repository.EmployeeRepository;
import no.asw.timeforing.utils.FilenameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Month;
import java.time.Year;
import java.util.List;

@Service
public class RevenueImporter extends AbstractImporter<RevenueLine> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean importFile(Path path) {
        List<RevenueLine> revenueLines;
        try {
            revenueLines = readLines(path, RevenueLine.class);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Month month = FilenameUtil.getMonthFromFileName(path.getFileName());
        Year year = FilenameUtil.getYearFromFilePath(path);

        revenueLines.forEach(line -> updateEmployee(line, year, month));
        return false;
    }

    private void updateEmployee(RevenueLine revenueLine, Year year, Month month) {
        Revenue revenue = createRevenueFromRevenueLine(revenueLine, month);
        Employee employee = employeeRepository.findOne(revenueLine.getEmployeeId());

        if(employee == null) employee = createNewEmployeeFromRevenue(revenueLine);

        employee.addRevenue(revenue, year);
        employeeRepository.save(employee);
    }

    private Employee createNewEmployeeFromRevenue(RevenueLine revenueLine) {
        Employee employee = new Employee(revenueLine.getEmployeeId());
        return employee;
    }


    private Revenue createRevenueFromRevenueLine(RevenueLine revenueLine, Month month) {
        return new Revenue(revenueLine, month);
    }
}
