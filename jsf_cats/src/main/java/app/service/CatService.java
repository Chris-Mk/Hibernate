package app.service;

import app.domain.model.CatCreateModel;
import app.domain.model.CatViewModel;

import java.util.List;

public interface CatService {

    void create(CatCreateModel catCreateModel);

    List<CatViewModel> getAllCats();
}
