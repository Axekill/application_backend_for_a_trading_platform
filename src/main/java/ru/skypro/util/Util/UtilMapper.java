package ru.skypro.util.Util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UtilMapper {
    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }
}

