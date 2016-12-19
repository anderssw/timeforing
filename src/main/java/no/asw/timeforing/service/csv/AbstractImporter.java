package no.asw.timeforing.service.csv;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractImporter<T>{

    public abstract boolean importFile(Path csv);

    public List<T> readLines(Path csvFile, Class<T> schemaClass) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(schemaClass).withColumnSeparator(';');

        List<T> result = new ArrayList<>();

        Files.lines(csvFile).forEach(line -> {
            try {
                result.add(mapper.readerFor(schemaClass).with(schema).readValue(line.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return result;
    }

}
