package no.asw.timeforing.utils;

import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;

public class FilenameUtilTest {

    @Test
    public void testThatYearIsFound(){
        Path path = Paths.get("/data/project/2016/enfinfil.csv");
        Year year = FilenameUtil.getYearFromFilePath(path);
        Assert.assertTrue(year.equals(Year.of(2016)));
    }

}
