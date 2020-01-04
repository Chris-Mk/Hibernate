package service;

import domain.entities.Employee;
import domain.model.service.EmployeeRegisterServiceModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import repository.EmployeeRepository;
import service.base.EmployeeService;

import javax.inject.Inject;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    @Inject
    public EmployeeServiceImpl(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void register(EmployeeRegisterServiceModel employeeRegisterServiceModel) {
        Employee employee = modelMapper.map(employeeRegisterServiceModel, Employee.class);
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeRegisterServiceModel> getAllEmployees() {
        return modelMapper
                .map(this.employeeRepository.findAll(),
                        new TypeToken<List<EmployeeRegisterServiceModel>>() {}.getType());
    }

    @Override
    public void delete(Long id) {
        this.employeeRepository.delete(id);
    }
}
