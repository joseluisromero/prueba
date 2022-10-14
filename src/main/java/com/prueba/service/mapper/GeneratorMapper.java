package com.prueba.service.mapper;

import com.prueba.service.dto.ClientInfoDto;
import com.prueba.service.dto.ClientInfoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.LocalDateTime;
import java.util.Date;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, Date.class})
public interface GeneratorMapper {
    @Mappings({
            @Mapping(target = "identification", source = "clientInfoDto.identification"),
            @Mapping(target = "unmaskedIdentification", source = "clientInfoDto.unmaskedIdentification"),
            @Mapping(target = "cardType", source = "clientInfoDto.cardType"),
            @Mapping(target = "toDay", expression = "java(new Date())", dateFormat = "yyyy-MM-dd HH:mm:ss"),
            //para poder coger el dato de un metodo publico mapeado en la entidad
            @Mapping(target = "fullNames", expression = "java(clientInfoDto.getFullName())")
    })
    ClientInfoResponseDto toAdditionalClientInfoResponseDto(ClientInfoDto clientInfoDto);
}
