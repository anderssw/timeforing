package no.asw.timeforing;

import no.asw.timeforing.repository.EmployeeRepository;
import no.asw.timeforing.service.csv.RevenueImporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class Startup {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RevenueImporter revenueImporter;

    @PostConstruct
    public void init() throws URISyntaxException, IOException {
        Path pathToCsvFiles = Paths.get((getClass().getResource("/Revenue Files/").toURI()));
        employeeRepository.deleteAll();

        Files.newDirectoryStream(pathToCsvFiles).forEach(file -> {
            try {
                revenueImporter.importFromFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
