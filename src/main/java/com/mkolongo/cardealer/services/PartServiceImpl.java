package com.mkolongo.cardealer.services;

import com.mkolongo.cardealer.dtos.seedDtos.PartSeedDto;
import com.mkolongo.cardealer.models.Part;
import com.mkolongo.cardealer.models.Supplier;
import com.mkolongo.cardealer.repositories.PartRepository;
import com.mkolongo.cardealer.repositories.SupplierRepository;
import com.mkolongo.cardealer.services.base.PartService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

@Service
public class PartServiceImpl implements PartService {

    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public PartServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository,
                           ModelMapper modelMapper, Validator validator) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public void seedPartsIntoDatabase(PartSeedDto[] partSeedDtos) {
        final Set<ConstraintViolation<PartSeedDto[]>> violations = validator.validate(partSeedDtos);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<PartSeedDto[]> violation : violations) {
                System.out.println(violation.getMessage());
            }
            return;
        }

        if (partRepository.count() == 0) {
            final Part[] parts = modelMapper.map(partSeedDtos, new TypeToken<Part[]>() {}.getType());
            final List<Supplier> suppliers = supplierRepository.findAll();

            for (Part part : parts) {
                final Supplier randomSupplier = getRandomSupplier(suppliers);
                part.setSupplier(randomSupplier);
            }

            partRepository.saveAll(Arrays.asList(parts));
        }
    }

    private Supplier getRandomSupplier(List<Supplier> suppliers) {
        Collections.shuffle(suppliers);
        return suppliers.get(0);
    }
}
