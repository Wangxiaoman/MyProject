package com;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class StreamTest {

    public static String join2Str(List<String> list) {
        return list.stream().filter(str -> StringUtils.isNotBlank(str))
                .collect(Collectors.joining(","));
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("");
        System.out.println(join2Str(list));
    }
}
