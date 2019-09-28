package app.ccb.services;

import app.ccb.domain.dtos.ClientImportDto;
import app.ccb.domain.dtos.FamilyGuyExportDto;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {

    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;

    @Autowired
    public ClientServiceImpl(EmployeeRepository employeeRepository, ClientRepository clientRepository,
                             ValidationUtil validationUtil, ModelMapper modelMapper, FileUtil fileUtil, Gson gson) {
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public Boolean clientsAreImported() {
        return this.clientRepository.count() != 0;
    }

    @Override
    public String readClientsJsonFile() throws IOException {
        return fileUtil.readFile("files/json/clients.json");
    }

    @Override
    public String importClients(String clients) {
        StringBuilder importResult = new StringBuilder();

        final ClientImportDto[] clientImportDtos = gson.fromJson(clients, ClientImportDto[].class);

        Map<Client, Set<Employee>> clientList = new LinkedHashMap<>();

        for (ClientImportDto clientImportDto : clientImportDtos) {
            if (!validationUtil.isValid(clientImportDto)) {
                importResult.append("Error: Invalid Data!");
                continue;
            }

            final Employee employee = employeeRepository.findEmployeeByFirstNameAndLastName(
                    clientImportDto.getAppointedEmployee().split("\\s")[0],
                    clientImportDto.getAppointedEmployee().split("\\s")[1]);

            if (employee == null) {
                importResult.append("Error: Invalid Data!");
                continue;
            }

            final Client client = modelMapper.map(clientImportDto, Client.class);
            client.setFullName(clientImportDto.getFirstName() + " " + clientImportDto.getLastName());

            if (!clientList.containsKey(client)) {
                clientList.put(client, new LinkedHashSet<>());
            }
            clientList.get(client).add(employee);

            importResult.append(String.format("Successfully imported Client - %s%n", clientImportDto.getAppointedEmployee()));
        }

        for (var clientSetEntry : clientList.entrySet()) {
            clientSetEntry.getKey().setEmployees(clientSetEntry.getValue());

            clientRepository.saveAndFlush(clientSetEntry.getKey());
        }

        return importResult.toString().trim();
    }

    @Override
    public String exportFamilyGuy() {
        final Client client = clientRepository.findClientWithMostCards().get(0);

        final FamilyGuyExportDto familyGuyExportDto = modelMapper.map(client, FamilyGuyExportDto.class);
        return familyGuyExportDto.toString();
    }
}
