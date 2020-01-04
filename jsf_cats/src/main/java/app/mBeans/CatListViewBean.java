package app.mBeans;

import app.domain.model.CatViewModel;
import app.service.CatService;
import lombok.NoArgsConstructor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
@NoArgsConstructor
public class CatListViewBean {
    private List<CatViewModel> cats;

    @Inject
    public CatListViewBean(CatService catService) {
        this.cats = catService.getAllCats();
    }

    public List<CatViewModel> getCats() {
        return cats;
    }

    public void setCats(List<CatViewModel> cats) {
        this.cats = cats;
    }
}
