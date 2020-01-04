package app.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.validation.Validation;
import javax.validation.Validator;

public class ApplicationBeanConfiguration {

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Produces
    public Validator validation() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Produces
    public EntityManager entityManager() {
        return Persistence.createEntityManagerFactory("meTube_v2").createEntityManager();
    }
}
