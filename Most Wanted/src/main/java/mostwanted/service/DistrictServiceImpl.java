package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final static String DISTRICT_JSON_FILE_PATH = System.getProperty("user.dir")
            + "/src/main/resources/files/districts.json";

    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;

    public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository,
                               ValidationUtil validationUtil, ModelMapper modelMapper, FileUtil fileUtil, Gson gson) {
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public Boolean districtsAreImported() {
        return districtRepository.count() != 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        return fileUtil.readFile(DISTRICT_JSON_FILE_PATH);
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        final DistrictImportDto[] districtImportDtos =
                gson.fromJson(districtsFileContent, new TypeToken<DistrictImportDto[]>() {}.getType());
        StringBuilder builder = new StringBuilder();

        for (DistrictImportDto districtImportDto : districtImportDtos) {
            if (!validationUtil.isValid(districtImportDto)) {
                builder.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            final Town town = townRepository.findTownByName(districtImportDto.getTownName());
            final District district = modelMapper.map(districtImportDto, District.class);
            district.setTown(town);

            try {
                districtRepository.saveAndFlush(district);
            } catch (Exception ex) {
                builder.append(Constants.DUPLICATE_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            builder.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "District", district.getName()))
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
