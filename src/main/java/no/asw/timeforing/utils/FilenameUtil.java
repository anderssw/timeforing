package no.asw.timeforing.utils;

import java.io.File;
import java.nio.file.Path;
import java.time.Month;
import java.time.Year;
import java.util.regex.Pattern;

public class FilenameUtil {

    public static Month getMonthFromFileName(Path fileName) {
        String month = fileName.toString().replace(".csv","").toUpperCase();
        return Month.valueOf(month);
    }

    public static Year getYearFromFileName(Path fileName) {
        String[] paths = fileName.toString().split(Pattern.quote(File.separator));
        String year = paths[paths.length - 2];
        return Year.of(Integer.parseInt(year));
    }

}
