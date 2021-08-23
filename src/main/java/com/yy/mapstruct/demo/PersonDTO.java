package com.yy.mapstruct.demo;

import lombok.Data;

import java.util.List;

@Data
public class PersonDTO {
    private Integer id;
    private String name;
    private List<TagDTO> tags;

    @Data
    public static class TagDTO {
        private Integer tagId;
        private String tagName;
    }
}
