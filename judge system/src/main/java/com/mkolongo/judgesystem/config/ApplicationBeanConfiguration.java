package com.mkolongo.judgesystem.config;

import com.mkolongo.judgesystem.util.ValidationUtilImpl;
import com.mkolongo.judgesystem.util.base.FileUtil;
import com.mkolongo.judgesystem.util.FileUtilImpl;
import com.mkolongo.judgesystem.util.base.Parser;
import com.mkolongo.judgesystem.util.ParserImpl;
import com.mkolongo.judgesystem.util.base.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public FileUtil fileUtil() {
        return new FileUtilImpl();
    }

    @Bean
    public Parser parser() {
        return new ParserImpl();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
