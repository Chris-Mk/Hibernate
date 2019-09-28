package app.ccb.services;

import app.ccb.domain.dtos.EmployeeImportDto;
import app.ccb.domain.dtos.TopEmployeesExportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BranchRepository branchRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, BranchRepository branchRepository, ValidationUtil validationUtil,
                               ModelMapper modelMapper, FileUtil fileUtil, Gson gson) {
        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() != 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return fileUtil.readFile("files/json/employees.json");
    }

    @Override
    public String importEmployees(String employees) {
        StringBuilder importResult = new StringBuilder();

        final EmployeeImportDto[] employeeImportDtos = gson.fromJson(employees, EmployeeImportDto[].class);

        for (EmployeeImportDto employeeImportDto : employeeImportDtos) {
            if (!validationUtil.isValid(employeeImportDto)) {
                importResult.append("Error: Invalid Data!");
                continue;
            }

            final Branch branch = branchRepository.findBranchByName(employeeImportDto.getBranchName());

            if (branch == null) {
                importResult.append("Error: Invalid Data!");
                continue;
            }

            final Employee employee = modelMapper.map(employeeImportDto, Employee.class);
            employee.setFirstName(employeeImportDto.getFullName().split("\\s")[0]);
            employee.setLastName(employeeImportDto.getFullName().split("\\s")[1]);
            employee.setStartedOn(LocalDate.parse(employeeImportDto.getStartedOn()));
            employee.setBranch(branch);

            employeeRepository.saveAndFlush(employee);

            importResult.append(String.format("Successfully imported Employee - %s%n",
                    employee.getFirstName() + " " + employee.getLastName()));
        }

        return importResult.toString().trim();
    }

    @Override
    public String exportTopEmployees() {
        final List<Employee> employees = employeeRepository.findAllEmployeesWithClients();

        StringBuilder exportResult = new StringBuilder();

        for (Employee employee : employees) {
            TopEmployeesExportDto topEmployeesExportDto = modelMapper.map(employee, TopEmployeesExportDto.class);
            topEmployeesExportDto.setFullName(employee.getFirstName() + " " + employee.getLastName());

            topEmployeesExportDto.setClientsNames(
                    employee.getClients()
                    .stream()
                    .map(Client::getFullName)
                    .collect(Collectors.toSet())
            );

            exportResult.append(topEmployeesExportDto)
                    .append(System.lineSeparator());
        }

        return exportResult.toString().trim();
    }
}
