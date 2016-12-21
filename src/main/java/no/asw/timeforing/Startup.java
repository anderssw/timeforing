package no.asw.timeforing;

import no.asw.timeforing.repository.EmployeeRepository;
import no.asw.timeforing.service.csv.ProjectImporter;
import no.asw.timeforing.service.csv.RevenueImporter;
import no.asw.timeforing.utils.FilenameUtil;
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
import java.time.Year;

@Service
public class Startup {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RevenueImporter revenueImporter;

    @Autowired
    private ProjectImporter projectImporter;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init() throws URISyntaxException, IOException {
        employeeRepository.deleteAll();

        importRevenueData();
        importProjectData();
    }

    private void importProjectData() throws URISyntaxException, IOException {
        logger.info("Importing project data");
        Path pathToCsvFiles = Paths.get(getClass().getResource("/data/project").toURI());
        Files.newDirectoryStream(pathToCsvFiles).forEach(years -> {
            try {
                Files.newDirectoryStream(years).forEach(file -> projectImporter.importFile(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private void importRevenueData() throws URISyntaxException, IOException{
        logger.info("Importing revenue data");
        Path pathToCsvFiles = Paths.get(getClass().getResource("/data/revenue").toURI());
        Files.newDirectoryStream(pathToCsvFiles).forEach(years -> {
            try {
                Files.newDirectoryStream(years).forEach(file -> revenueImporter.importFile(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
