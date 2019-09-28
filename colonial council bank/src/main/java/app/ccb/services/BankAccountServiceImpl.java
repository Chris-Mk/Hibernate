package app.ccb.services;

import app.ccb.domain.dtos.BankAccountImportDto;
import app.ccb.domain.dtos.BankAccountListImportDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ClientRepository clientRepository,
                                  ValidationUtil validationUtil, ModelMapper modelMapper, FileUtil fileUtil) {
        this.bankAccountRepository = bankAccountRepository;
        this.clientRepository = clientRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean bankAccountsAreImported() {
        return this.bankAccountRepository.count() != 0;
    }

    @Override
    public String readBankAccountsXmlFile() throws IOException {
        return fileUtil.readFile("files/xml/bank-accounts.xml");
    }

    @Override
    public String importBankAccounts() throws JAXBException, IOException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(BankAccountListImportDto.class);

        StringReader reader = new StringReader(readBankAccountsXmlFile());
        final BankAccountListImportDto bankAccountImportDtos =
                (BankAccountListImportDto) jaxbContext.createUnmarshaller().unmarshal(reader);

        StringBuilder importResult = new StringBuilder();

        for (BankAccountImportDto bankAccountImportDto : bankAccountImportDtos.getBankAccounts()) {
            if (!validationUtil.isValid(bankAccountImportDto)) {
                importResult.append("Error: Invalid Data!");
                continue;
            }

            final Client client = clientRepository.findClientByFullName(bankAccountImportDto.getClient());

            if (client == null) {
                importResult.append("Error: Invalid Data!");
                continue;
            }

            final BankAccount bankAccount = modelMapper.map(bankAccountImportDto, BankAccount.class);
            bankAccount.setClient(client);

            bankAccountRepository.saveAndFlush(bankAccount);

            importResult.append(String.format("Successfully imported BankAccount - %s%n", bankAccount.getAccountNumber()));
        }

        return importResult.toString().trim();
    }
}
