package mostwanted.service;

import com.google.gson.Gson;
import javassist.bytecode.ConstantAttribute;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.CarImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CarServiceImpl implements CarService{

    private final static String CARS_JSON_FILE_PATH = System.getProperty("user.dir")
            +"/src/main/resources/files/cars.json";

    private final RacerRepository racerRepository;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final Gson gson;

    public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository, ValidationUtil validationUtil,
                          ModelMapper modelMapper, FileUtil fileUtil, Gson gson) {
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.gson = gson;
    }

    @Override
    public Boolean carsAreImported() {
        return carRepository.count() != 0;
    }

    @Override
    public String readCarsJsonFile() throws IOException {
        return fileUtil.readFile(CARS_JSON_FILE_PATH);
    }

    @Override
    public String importCars(String carsFileContent) {
        final CarImportDto[] carImportDtos = gson.fromJson(carsFileContent, new TypeToken<CarImportDto[]>() {}.getType());
        StringBuilder builder = new StringBuilder();

        for (CarImportDto carImportDto : carImportDtos) {
            if (!validationUtil.isValid(carImportDto)) {
                builder.append(Constants.INCORRECT_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            final Racer racer = racerRepository.findRacerByName(carImportDto.getRacerName());
            final Car car = modelMapper.map(carImportDto, Car.class);
            car.setRacer(racer);

            try {
                carRepository.saveAndFlush(car);
            } catch (Exception ex) {
                builder.append(Constants.DUPLICATE_DATA_MESSAGE)
                        .append(System.lineSeparator());
                continue;
            }

            builder.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Car",
                    String.format("%s %s @ %d",
                            car.getBrand(),
                            car.getModel(),
                            car.getYearOfProduction())))
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
