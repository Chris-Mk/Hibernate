package app.service;

import app.domain.entity.Cat;
import app.domain.model.CatCreateModel;
import app.domain.model.CatViewModel;
import app.repository.CatRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final ModelMapper modelMapper;

    @Inject
    public CatServiceImpl(CatRepository catRepository, ModelMapper modelMapper) {
        this.catRepository = catRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(CatCreateModel catCreateModel) {
        Cat cat = modelMapper.map(catCreateModel, Cat.class);
        cat.setAddedOn(LocalDate.parse(catCreateModel.getAddedOn(), DateTimeFormatter.ISO_LOCAL_DATE));
        catRepository.save(cat);
    }

    @Override
    public List<CatViewModel> getAllCats() {
        return modelMapper.map(catRepository.findAll(), new TypeToken<List<CatViewModel>>() {}.getType());
    }
}
