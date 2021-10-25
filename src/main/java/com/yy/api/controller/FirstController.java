package com.yy.api.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.yy.mapstruct.ConvertMapper;
import com.yy.mapstruct.demo.CatDTO;
import com.yy.mapstruct.demo.CatVO;
import com.yy.mapstruct.demo.PersonDTO;
import com.yy.mapstruct.demo.PersonVO;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

@RestController
@RequestMapping(path = "/api/first")
public class FirstController {

    @Resource
    private RedissonClient redissonClient;
    @Resource
    private Redisson redisson;

    @GetMapping("/redisson")
    public String redisson() {

        RBloomFilter<Object> bloom = redisson.getBloomFilter("bloom");
        bloom.tryInit(500000L,0.03);

        return "";
    }

    @GetMapping("/hello")
    public String first() {
        return "Hello world!";
    }

    @GetMapping(value = "/testMapstruct", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO mapstruct() throws JsonProcessingException {
        CatDTO catDTO = new CatDTO();
        catDTO.setId(10085);
        catDTO.setName("aaaa");
        CatVO catVO = ConvertMapper.INSTANCE.catDto2Vo(catDTO);



        PersonDTO personDTO = create();
        PersonVO personVO = ConvertMapper.INSTANCE.personDto2Vo(personDTO);
        return personVO;
    }



    private PersonDTO create() {
        PersonDTO person = new PersonDTO();
        person.setId(123);
        person.setName("xiaoming");

        PersonDTO.TagDTO tag1 = new PersonDTO.TagDTO();
        tag1.setTagId(1000);
        tag1.setTagName("工科");
        PersonDTO.TagDTO tag2 = new PersonDTO.TagDTO();
        tag2.setTagId(2000);
        tag2.setTagName("博士");

        person.setTags(Arrays.asList(tag1, tag2));

        return person;

    }
}
