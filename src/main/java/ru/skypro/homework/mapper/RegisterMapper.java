package ru.skypro.homework.mapper;

import jdk.vm.ci.code.Register;
import org.mapstruct.extensions.spring.SpringMapperConfig;
import ru.skypro.homework.dto.RegisterDTO;

@SpringMapperConfig
public interface RegisterMapper {

    RegisterDTO toDto(Register register);

    Register toEntity(RegisterDTO registerDTO);
}
