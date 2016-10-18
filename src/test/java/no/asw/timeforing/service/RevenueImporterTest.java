package no.asw.timeforing.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RevenueImporterTest {


    @Autowired
    private RevenueImporter importer;

    @Test
    public void testThatCSVFileCanBeImported() throws IOException, URISyntaxException {

        Path path = Paths.get(getClass().getResource("/Revenue Files/september.csv").toURI());

        importer.importFromFile(path);

    }

}
