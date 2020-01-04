package service.base;

import domain.model.service.EmployeeRegisterServiceModel;

import java.util.List;

public interface EmployeeService {

    void register(EmployeeRegisterServiceModel employeeRegisterServiceModel);

    List<EmployeeRegisterServiceModel> getAllEmployees();

    void delete(Long id);
}
