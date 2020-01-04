package com.mkolongo.cardealer.services;

import com.mkolongo.cardealer.dtos.seedDtos.SupplierSeedDto;
import com.mkolongo.cardealer.dtos.viewDtos.SupplierViewDto;
import com.mkolongo.cardealer.models.Supplier;
import com.mkolongo.cardealer.repositories.SupplierRepository;
import com.mkolongo.cardealer.services.base.SupplierService;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, Validator validator) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public void seedSuppliersIntoDatabase(SupplierSeedDto[] supplierSeedDtos) {
        final Set<ConstraintViolation<SupplierSeedDto[]>> violations = validator.validate(supplierSeedDtos);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<SupplierSeedDto[]> violation : violations) {
                System.out.println(violation.getMessage());
            }
            return;
        }

        if (supplierRepository.count() == 0) {
            final Supplier[] suppliers =
                    modelMapper.map(supplierSeedDtos, new TypeToken<Supplier[]>() {
                    }.getType());

            supplierRepository.saveAll(Arrays.asList(suppliers));
        }
    }

    @Override
    @Transactional
    public List<SupplierViewDto> getAllLocalSuppliers() {
//        ???
//        Converter<Supplier, Integer> getSuppliersPartsCount = new AbstractConverter<Supplier, Integer>() {
//            @Override
//            protected Integer convert(Supplier source) {
//                return source.getParts().size();
//            }
//        };
//
//        modelMapper.createTypeMap(Supplier.class, SupplierViewDto.class)
//                .addMappings(mapping -> mapping.using(getSuppliersPartsCount)
//                        .map(source -> source,
//                                (destination, value) -> destination.setPartsCount((int) value)
//                ));

        List<SupplierViewDto> supplierViewDtos = new ArrayList<>();

        supplierRepository.findSuppliersByIsImporterFalse()
                .forEach(supplier -> {
                    final SupplierViewDto supplierViewDto = modelMapper.map(supplier, SupplierViewDto.class);
                    supplierViewDto.setPartsCount(supplier.getParts().size());

                    supplierViewDtos.add(supplierViewDto);
                });

        return supplierViewDtos;
    }
}
