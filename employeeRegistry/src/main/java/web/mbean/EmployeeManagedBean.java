package web.mbean;

import domain.model.binding.EmployeeRegisterBindingModel;
import domain.model.service.EmployeeRegisterServiceModel;
import org.modelmapper.ModelMapper;
import service.base.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class EmployeeManagedBean {

    private ModelMapper modelMapper;
    private EmployeeService employeeService;
    private EmployeeRegisterBindingModel employeeRegisterBindingModel;

    public EmployeeManagedBean() {}

    @Inject
    public EmployeeManagedBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employeeRegisterBindingModel = new EmployeeRegisterBindingModel();
    }

    public void register() throws IOException {
        EmployeeRegisterServiceModel employeeRegisterServiceModel =
                this.modelMapper.map(employeeRegisterBindingModel, EmployeeRegisterServiceModel.class);

        employeeService.register(employeeRegisterServiceModel);

        FacesContext.getCurrentInstance().getExternalContext().redirect("/");
    }

    public void remove(Long id) {
        this.employeeService.delete(id);
    }

    public EmployeeRegisterBindingModel getEmployeeRegisterBindingModel() {
        return employeeRegisterBindingModel;
    }

    public void setEmployeeRegisterBindingModel(EmployeeRegisterBindingModel employeeRegisterBindingModel) {
        this.employeeRegisterBindingModel = employeeRegisterBindingModel;
    }
}
