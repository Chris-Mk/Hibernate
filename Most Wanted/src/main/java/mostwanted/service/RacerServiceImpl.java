package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.RacerImportDto;
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

@Service
public class RacerServiceImpl implements RacerService {

    private final static String RACERS_JSON_FILE_PATH = System.getProperty("user.dir")
            + "/src/main/resources/files/racers.json";

    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;

    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, ValidationUtil validationUtil,
                            ModelMapper modelMapper, FileUtil fileUtil, Gson gson) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public Boolean racersAreImported() {
        return racerRepository.count() != 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        return fileUtil.readFile(RACERS_JSON_FILE_PATH);
    }

    @Override
    public String importRacers(String racersFileContent) {
        final RacerImportDto[] racerImportDtos =
                gson.fromJson(racersFileContent, new TypeToken<RacerImportDto[]>() {}.getType());
        StringBuilder builder = new StringBuilder();

        for (RacerImportDto racerImportDto : racerImportDtos) {
            if (!validationUtil.isValid(racerImportDto)) {
                builder.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            final Town town = townRepository.findTownByName(racerImportDto.getHomeTown());
            final Racer racer = modelMapper.map(racerImportDto, Racer.class);
            racer.setHomeTown(town);

            try {
                racerRepository.saveAndFlush(racer);
            } catch (Exception ex) {
                builder.append(Constants.DUPLICATE_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            builder.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Racer", racer.getName()))
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }

    @Override
    public String exportRacingCars() {
        StringBuilder builder = new StringBuilder();
        racerRepository.findRacersByCarsNotNull()
                .stream()
                .sorted((r1, r2) -> {
                    int sort = Integer.compare(r2.getCars().size(), r1.getCars().size());

                    if (sort == 0) {
                        sort = r1.getName().compareTo(r2.getName());
                    }
                    return sort;
                })
                .forEach(racer -> {
                    builder.append(String.format("Name: %s", racer.getName()))
                            .append(System.lineSeparator());

                    if (racer.getAge() != 0) {
                        builder.append(String.format("Age: %d", racer.getAge()))
                                .append(System.lineSeparator());
                    }

                    builder.append("Cars:").append(System.lineSeparator());
                    racer.getCars()
                            .forEach(car -> builder.append("\t")
                                    .append(String.format("%s %s %d",
                                    car.getBrand(),
                                    car.getModel(),
                                    car.getYearOfProduction()))
                                    .append(System.lineSeparator()));
                    builder.append(System.lineSeparator());
                });

        return builder.toString().trim();
    }
}
