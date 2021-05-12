package com.data.protein;

import java.util.List;

import lombok.Data;

@Data
public class Topic {
    private String name;
    private int level;
    private List<String> parentName;
}
