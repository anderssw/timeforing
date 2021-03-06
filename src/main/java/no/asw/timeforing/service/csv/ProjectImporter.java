package no.asw.timeforing.service.csv;

import no.asw.timeforing.domain.Employee;
import no.asw.timeforing.domain.Project;
import no.asw.timeforing.domain.csv.ProjectLine;
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
public class ProjectImporter extends AbstractImporter<ProjectLine> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public boolean importFile(Path path) {
        List<ProjectLine> projectLines;
        try {
            projectLines = readLines(path, ProjectLine.class);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        Month month = FilenameUtil.getMonthFromFileName(path.getFileName());
        Year year = FilenameUtil.getYearFromFileName(path);

        projectLines.forEach(line -> updateEmployee(line, year, month));
        return false;
    }

    private void updateEmployee(ProjectLine projectLine, Year year, Month month) {
        Employee employee = employeeRepository.findOne(projectLine.getEmployeeId());
        if(employee == null) employee = createNewEmployee(projectLine.getEmployeeId());
        Project project = createProjectFromProjectLine(projectLine, month);
        employee.addProject(project, year);
        employeeRepository.save(employee);
    }


    private Employee createNewEmployee(Long employeeId) {
        Employee employee = new Employee(employeeId, 0);
        return employee;
    }

    private Project createProjectFromProjectLine(ProjectLine projectLine, Month month) {
        return new Project(projectLine, month);
    }

    private Month getMonthFromFileName(Path fileName) {
        String month = fileName.toString().replace(".csv","").toUpperCase();
        return Month.valueOf(month);
    }

}

