package app.mBeans;

import app.domain.model.CatCreateModel;
import app.service.CatService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CatCreateBean {
    private CatCreateModel catCreateModel;
    private CatService catService;

    public CatCreateBean() {}

    @Inject
    public CatCreateBean(CatService catService) {
        this.catService = catService;
        this.catCreateModel = new CatCreateModel();
    }

    public void createCat() throws IOException {
        catService.create(catCreateModel);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/");
    }

    public CatCreateModel getCatCreateModel() {
        return catCreateModel;
    }

    public void setCatCreateModel(CatCreateModel catCreateModel) {
        this.catCreateModel = catCreateModel;
    }
}
