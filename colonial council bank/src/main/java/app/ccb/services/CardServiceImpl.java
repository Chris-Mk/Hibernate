package app.ccb.services;

import app.ccb.domain.dtos.CardImportDto;
import app.ccb.domain.dtos.CardListImportDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Card;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.CardRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringReader;

@Service
public class CardServiceImpl implements CardService {

    private final BankAccountRepository bankAccountRepository;
    private final CardRepository cardRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    public CardServiceImpl(BankAccountRepository bankAccountRepository, CardRepository cardRepository,
                           ValidationUtil validationUtil, ModelMapper modelMapper, FileUtil fileUtil) {
        this.bankAccountRepository = bankAccountRepository;
        this.cardRepository = cardRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean cardsAreImported() {
        return this.cardRepository.count() != 0;
    }

    @Override
    public String readCardsXmlFile() throws IOException {
        return fileUtil.readFile("files/xml/cards.xml");
    }

    @Override
    public String importCards() throws JAXBException, IOException {
        final JAXBContext jaxbContext = JAXBContext.newInstance(CardListImportDto.class);

        StringReader reader = new StringReader(readCardsXmlFile());
        final CardListImportDto cardListImportDto = (CardListImportDto) jaxbContext.createUnmarshaller().unmarshal(reader);

        StringBuilder importResult = new StringBuilder();

        for (CardImportDto card : cardListImportDto.getCards()) {
            if (!validationUtil.isValid(card)) {
                importResult.append("Error: Invalid Data!");
                continue;
            }

            final BankAccount bankAccount = bankAccountRepository.findBankAccountByAccountNumber(card.getBankAccountNumber());

            if (bankAccount == null) {
                importResult.append("Error: Invalid Data!");
                continue;
            }

            final Card card1 = modelMapper.map(card, Card.class);
            card1.setBankAccount(bankAccount);

            cardRepository.saveAndFlush(card1);

            importResult.append(String.format("Successfully imported Card - %s%n", card.getCardNumber()));
        }

        return importResult.toString().trim();
    }
}
