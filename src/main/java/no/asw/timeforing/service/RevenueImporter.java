package no.asw.timeforing.service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import no.asw.timeforing.domain.Employee;
import no.asw.timeforing.domain.Revenue;
import no.asw.timeforing.domain.csv.RevenueLine;
import no.asw.timeforing.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Month;

@Service
public class RevenueImporter {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void importFromFile(Path csvFile) throws IOException {
        Month month = getMonthFromFileName(csvFile.getFileName());

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(RevenueLine.class).withColumnSeparator(';');

        Files.lines(csvFile).forEach(line -> {
            try {
                RevenueLine revenueLine = mapper.readerFor(RevenueLine.class).with(schema).readValue(line.getBytes());
                updateEmployee(revenueLine, month);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateEmployee(RevenueLine revenueLine, Month month) {
        Revenue revenue = createRevenueFromRevenueLine(revenueLine, month);
        Employee employee = employeeRepository.findOne(revenueLine.getEmployeeId());

        if(employee == null) employee = createNewEmployeeFromRevenue(revenueLine);

        employee.addRevenue(revenue);
        employeeRepository.save(employee);
    }

    private Employee createNewEmployeeFromRevenue(RevenueLine revenueLine) {
        Employee employee = new Employee(revenueLine.getEmployeeId(), 0);
        return employee;
    }

    private Revenue createRevenueFromRevenueLine(RevenueLine revenueLine, Month month) {
        return new Revenue(revenueLine, month);
    }

    private Month getMonthFromFileName(Path fileName) {
        String month = fileName.toString().replace(".csv","").toUpperCase();
        return Month.valueOf(month);
    }

}
