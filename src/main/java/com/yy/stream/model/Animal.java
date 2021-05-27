package com.yy.stream.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Animal {
    private int id;
    private String name;
}
