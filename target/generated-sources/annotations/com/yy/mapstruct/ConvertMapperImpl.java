package com.yy.mapstruct;

import com.yy.mapstruct.demo.CatDTO;
import com.yy.mapstruct.demo.CatVO;
import com.yy.mapstruct.demo.PersonDTO;
import com.yy.mapstruct.demo.PersonDTO.TagDTO;
import com.yy.mapstruct.demo.PersonVO;
import com.yy.mapstruct.demo.PersonVO.TagVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-24T00:18:38+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
public class ConvertMapperImpl implements ConvertMapper {

    @Override
    public PersonVO personDto2Vo(PersonDTO person) {
        if ( person == null ) {
            return null;
        }

        PersonVO personVO = new PersonVO();

        personVO.setFirstName( person.getName() );
        personVO.setPersonTags( tagDTOListToTagVOList( person.getTags() ) );
        personVO.setId( person.getId() );

        return personVO;
    }

    @Override
    public TagVO tagDTO2VO(TagDTO tagDTO) {
        if ( tagDTO == null ) {
            return null;
        }

        TagVO tagVO = new TagVO();

        tagVO.setName( tagDTO.getTagName() );
        tagVO.setId( tagDTO.getTagId() );

        return tagVO;
    }

    @Override
    public CatVO catDto2Vo(CatDTO cat) {
        if ( cat == null ) {
            return null;
        }

        CatVO catVO = new CatVO();

        catVO.setId( cat.getId() );
        catVO.setName( cat.getName() );

        return catVO;
    }

    protected List<TagVO> tagDTOListToTagVOList(List<TagDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<TagVO> list1 = new ArrayList<TagVO>( list.size() );
        for ( TagDTO tagDTO : list ) {
            list1.add( tagDTO2VO( tagDTO ) );
        }

        return list1;
    }
}
