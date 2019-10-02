package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.TownImportDto;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService{

    private final static String TOWNS_JSON_FILE_PATH = System.getProperty("user.dir")
            + "/src/main/resources/files/towns.json";

    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;

    public TownServiceImpl(RacerRepository racerRepository, TownRepository townRepository, ValidationUtil validationUtil,
                           ModelMapper modelMapper, FileUtil fileUtil, Gson gson) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public Boolean townsAreImported() {
        return townRepository.count() != 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return fileUtil.readFile(TOWNS_JSON_FILE_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) {
        final TownImportDto[] townImportDtos = gson.fromJson(townsFileContent, new TypeToken<TownImportDto[]>() {}.getType());
        StringBuilder builder = new StringBuilder();

        for (TownImportDto townImportDto : townImportDtos) {
            if (!validationUtil.isValid(townImportDto)) {
                builder.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            final Town town = modelMapper.map(townImportDto, Town.class);

            try {
                townRepository.saveAndFlush(town);
            } catch (Exception ex) {
                builder.append(Constants.DUPLICATE_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            builder.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Town", town.getName()))
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }

    @Override
    public String exportRacingTowns() {
        StringBuilder builder = new StringBuilder();
        racerRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Racer::getHomeTown))
                .entrySet()
                .stream()
                .sorted((t1, t2) -> {
                    int sort = Integer.compare(t2.getValue().size(), t1.getValue().size());

                    if (sort == 0) {
                        sort = t1.getKey().getName().compareTo(t2.getKey().getName());
                    }
                    return sort;
                })
                .forEach(townListEntry -> builder.append(String.format("Name: %s", townListEntry.getKey().getName()))
                        .append(System.lineSeparator())
                        .append(String.format("Racers: %d", townListEntry.getValue().size()))
                        .append(System.lineSeparator())
                        .append(System.lineSeparator()));
        return builder.toString().trim();
    }
}
