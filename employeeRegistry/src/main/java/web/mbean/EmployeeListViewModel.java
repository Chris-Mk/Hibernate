package web.mbean;

import domain.model.view.EmployeeViewModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import service.base.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class EmployeeListViewModel {
    private List<EmployeeViewModel> employeeViewModels;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeListViewModel() {}

    @Inject
    public EmployeeListViewModel(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employeeViewModels = modelMapper
                .map(employeeService.getAllEmployees(), new TypeToken<List<EmployeeViewModel>>() {}.getType());
    }

    public List<EmployeeViewModel> getEmployeeViewModels() {
        return employeeViewModels;
    }

    public void setEmployeeViewModels(List<EmployeeViewModel> employeeViewModels) {
        this.employeeViewModels = employeeViewModels;
    }
}
