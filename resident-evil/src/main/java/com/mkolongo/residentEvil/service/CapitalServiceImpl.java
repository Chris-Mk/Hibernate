package com.mkolongo.residentEvil.service;

import com.mkolongo.residentEvil.domain.entities.Capital;
import com.mkolongo.residentEvil.repository.CapitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CapitalServiceImpl implements CapitalService {

    private final CapitalRepository capitalRepository;

    @Override
    public List<String> getAllCapitalNames() {
        return capitalRepository.findAll()
                .stream()
                .map(Capital::getName)
                .sorted()
                .collect(Collectors.toList());
    }
}
