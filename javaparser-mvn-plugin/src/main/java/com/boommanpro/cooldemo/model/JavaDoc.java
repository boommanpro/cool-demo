package com.boommanpro.cooldemo.model;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JavaDoc {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, String> getTagMap() {
        return tagMap;
    }

    private final Map<String, String> tagMap;

    public JavaDoc(String content) {
        tagMap = new HashMap<>();
        Arrays.stream(content.split("\n")).forEach(s -> {
            s = handlerStartAsterisk(s);
            if (StringUtils.isBlank(s)) {
                return;
            }
            if (s.contains("@")) {
                String[] value = s.split(" ", 2);
                tagMap.put(value[0].substring(1), value[1]);
                return;
            }
            title = s;
        });
    }

    private String handlerStartAsterisk(String s) {
        s = s.trim();
        if (s.startsWith("*")) {
            s = s.substring(1).trim();
        }
        return s;
    }
}
