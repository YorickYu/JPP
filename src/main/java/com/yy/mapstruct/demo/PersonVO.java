package com.yy.mapstruct.demo;

import lombok.Data;

import java.util.List;

@Data
public class PersonVO {
    private Integer id;
    private String firstName;
    private String fullName;
    private List<TagVO> personTags;
    private List<String> tags;

    @Data
    public static class TagVO {
        private Integer id;
        private String name;
    }
}
