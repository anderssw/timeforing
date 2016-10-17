package no.asw.timeforing.service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import no.asw.timeforing.domain.Employee;
import no.asw.timeforing.domain.Revenue;
import no.asw.timeforing.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RevenueImporter {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void importFromFile(Path csvFile) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(Revenue.class).withColumnSeparator(';');
        Files.lines(csvFile).forEach(line -> {
            try {
                Revenue revenue = mapper.readerFor(Revenue.class).with(schema).readValue(line.getBytes());
                updateEmployee(revenue);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateEmployee(Revenue revenue) {
        Employee employee = employeeRepository.findOne(revenue.getEmployeeId());
        if(employee == null) employee = createNewEmployeeFromRevenue(revenue);
        employee.addHours(revenue.getBillableHours().replace(',','.'));
        employeeRepository.save(employee);
    }

    private Employee createNewEmployeeFromRevenue(Revenue revenue) {
        Employee employee = new Employee(revenue.getEmployeeId(), 0);
        return employee;
    }

}
