package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.races.RaceImportDto;
import mostwanted.domain.dtos.races.RaceImportRootDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.apache.tomcat.util.bcel.Const;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaceServiceImpl implements RaceService {

    private static int id = 1;
    private final static String RACES_XML_FILE_PATH = System.getProperty("user.dir")
            + "/src/main/resources/files/races.xml";

    private final RaceEntryRepository raceEntryRepository;
    private final DistrictRepository districtRepository;
    private final RaceRepository raceRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final FileUtil fileUtil;

    public RaceServiceImpl(RaceEntryRepository raceEntryRepository, DistrictRepository districtRepository,
                           RaceRepository raceRepository, ValidationUtil validationUtil, ModelMapper modelMapper,
                           XmlParser xmlParser, FileUtil fileUtil) {
        this.raceEntryRepository = raceEntryRepository;
        this.districtRepository = districtRepository;
        this.raceRepository = raceRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean racesAreImported() {
        return raceRepository.count() != 0;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        return fileUtil.readFile(RACES_XML_FILE_PATH);
    }

    @Override
    public String importRaces() throws JAXBException, IOException {
        final RaceImportRootDto raceImportRootDto = xmlParser.parseXml(RaceImportRootDto.class, readRacesXmlFile());
        StringBuilder builder = new StringBuilder();

        for (RaceImportDto raceImportDto : raceImportRootDto.getRaces()) {
            if (!validationUtil.isValid(raceImportDto)) {
                builder.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            final District district = districtRepository.findDistinctByName(raceImportDto.getDistrictName());
            final List<RaceEntry> raceEntries = raceImportDto.getEntries()
                    .getEntries()
                    .stream()
                    .map(entryImportDto -> raceEntryRepository.findRaceEntryById(entryImportDto.getId()))
                    .collect(Collectors.toList());

            final Race race = modelMapper.map(raceImportDto, Race.class);
            race.setDistrict(district);
            race.setEntries(raceEntries);
            race.setId(id++);

            raceRepository.saveAndFlush(race);
            builder.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Race", race.getId()))
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}