package no.asw.timeforing;

import no.asw.timeforing.repository.EmployeeRepository;
import no.asw.timeforing.service.csv.RevenueImporter;
import org.hibernate.validator.internal.util.logging.Log_$logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(this.getClass());

    //@PostConstruct
    public void init() throws URISyntaxException, IOException {
        Path pathToCsvFiles = Paths.get((getClass().getResource("/data/").toURI()));
        employeeRepository.deleteAll();

        Files.newDirectoryStream(pathToCsvFiles).forEach(file -> {
            try {
                logger.info("Importing data from file: " + file.toString());
                revenueImporter.importFromFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
