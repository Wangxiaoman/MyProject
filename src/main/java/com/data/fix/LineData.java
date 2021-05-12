package com.data.fix;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.google.common.base.Splitter;

import lombok.Data;

@Data
class LineData {
    private int lineNum;
    private String en;
    private String ch;

    public LineData() {}

    public LineData(String line) {
        List<String> contents = Splitter.on("#").splitToList(line);
        if (!CollectionUtils.isEmpty(contents) && contents.size() == 3) {
            this.lineNum = Integer.valueOf(contents.get(0));
            this.ch = contents.get(1);
            this.en = contents.get(2);
        }
    }
}
