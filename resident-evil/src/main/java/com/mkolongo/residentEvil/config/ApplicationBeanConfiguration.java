package com.mkolongo.residentEvil.config;

import com.mkolongo.residentEvil.domain.entities.Virus;
import com.mkolongo.residentEvil.domain.models.view.VirusViewModel;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        final Converter<LocalDate, String> localDateToStringConverter = new AbstractConverter<>() {
            @Override
            protected String convert(LocalDate date) {
                return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            }
        };

        modelMapper.createTypeMap(Virus.class, VirusViewModel.class)
                .addMappings(mapping ->
                        mapping.using(localDateToStringConverter)
                                .map(Virus::getReleaseDate,
                                        (destination, value) -> destination.setReleaseDate(((String) value))));

//        modelMapper.createTypeMap(User.class, UserViewModel.class)
//                .addMappings(mapping -> mapping.map(source -> source.getAuthorities(),
//                        (destination, value) -> {
//                            Set<String> roles = new HashSet<>();
//
//                            ((Set<Role>) value).forEach(role -> roles.add(role.getAuthority()));
//
//                            destination.setAuthorities(roles);
//                        }));

        return modelMapper;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtPropertiesConfig jwtPropertiesConfig() {
        return new JwtPropertiesConfig();
    }
}
