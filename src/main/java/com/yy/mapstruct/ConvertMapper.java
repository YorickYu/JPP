package com.yy.mapstruct;

import com.yy.mapstruct.demo.CatDTO;
import com.yy.mapstruct.demo.CatVO;
import com.yy.mapstruct.demo.PersonDTO;
import com.yy.mapstruct.demo.PersonVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


//@Mapper(componentModel = "default", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConvertMapper {

    ConvertMapper INSTANCE = Mappers.getMapper(ConvertMapper.class);

    @Mappings(value = {
            @Mapping(source = "name", target = "firstName"),
            @Mapping(target = "tags", ignore = true),
            @Mapping(source = "tags", target = "personTags")
    })
    abstract PersonVO personDto2Vo(PersonDTO person);

    @Mappings(value = {
            @Mapping(source = "tagId", target = "id"),
            @Mapping(source = "tagName", target = "name")
    })
    PersonVO.TagVO tagDTO2VO(PersonDTO.TagDTO tagDTO);

//    @AfterMapping
//    void afterDTO2VO(PersonDTO personDTO, @MappingTarget PersonVO personVO);


    CatVO catDto2Vo(CatDTO cat);

}
