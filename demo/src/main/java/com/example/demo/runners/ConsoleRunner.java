package com.example.demo.runners;

import com.example.demo.entities.Employee;
import com.example.demo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) {
//        final var employee = this.employeeRepository.findById((long) 1).orElse(null);

//        ModelMapper modelMapper = new ModelMapper();

//        final EmployeeDto employeeEmployeeDtoTypeMap = modelMapper
//                .createTypeMap(Employee.class, EmployeeDto.class)
//                .addMappings(mapping -> {
//                    mapping.map(Employee::getFirstName, (destination, value) -> destination.setFirstName((String) value));
//                    mapping.map(Employee::getLastName, (destination, value) -> destination.setLastName((String) value));
//                    mapping.map(Employee::getSalary, (destination, value) -> destination.setSalary((BigDecimal) value));
//                })
//                .map(employee);
//
//        final EmployeeDto employeeDto = modelMapper.addMappings(new PropertyMap<Employee, EmployeeDto>() {
//            @Override
//            protected void configure() {
//                map().setSalary(source.getSalary());
//                map().setFirstName(source.getFirstName());
//                map().setLastName(source.getLastName());
//            }
//        }).map(employee);

//        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

//        System.out.println(employeeDto);

        Employee employee = new Employee();
        employee.setFirstName("Fred");
        employee.setLastName("Eddy Mk");
        employee.setSalary(new BigDecimal(34785));
        employee.setBirthday(null);
        employee.setAddress("City");

        this.employeeRepository.save(employee);
    }
}
