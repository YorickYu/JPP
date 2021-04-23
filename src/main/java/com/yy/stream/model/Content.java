package com.yy.stream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
        private String name;

        private String image;

        private String desc;

        private Integer width;

        private Integer height;
}