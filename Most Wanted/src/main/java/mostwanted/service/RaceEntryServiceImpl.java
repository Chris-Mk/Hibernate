package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.raceentries.RaceEntryImportDto;
import mostwanted.domain.dtos.raceentries.RaceEntryImportRootDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

    private static int id = 1;
    private final static String RACE_ENTRIES_XML_FILE_PATH = System.getProperty("user.dir")
            + "/src/main/resources/files/race-entries.xml";

    private final RaceEntryRepository raceEntryRepository;
    private final RacerRepository racerRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;

    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, RacerRepository racerRepository,
                                CarRepository carRepository, ModelMapper modelMapper, XmlParser xmlParser,
                                FileUtil fileUtil) {
        this.raceEntryRepository = raceEntryRepository;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean raceEntriesAreImported() {
        return raceEntryRepository.count() != 0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        return fileUtil.readFile(RACE_ENTRIES_XML_FILE_PATH);
    }

    @Override
    public String importRaceEntries() throws IOException, JAXBException {
        final RaceEntryImportRootDto raceEntryImportRootDto =
                xmlParser.parseXml(RaceEntryImportRootDto.class, readRaceEntriesXmlFile());
        StringBuilder builder = new StringBuilder();

        for (RaceEntryImportDto raceEntry : raceEntryImportRootDto.getRaceEntries()) {
            final Car car = carRepository.findCarById(raceEntry.getCarId());
            final Racer racer = racerRepository.findRacerByName(raceEntry.getRacerName());

            final RaceEntry entry = modelMapper.map(raceEntry, RaceEntry.class);
            entry.setCar(car);
            entry.setRacer(racer);
            entry.setRace(null);
            entry.setId(id++);

            raceEntryRepository.saveAndFlush(entry);
            builder.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Race Entry", entry.getId()))
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
