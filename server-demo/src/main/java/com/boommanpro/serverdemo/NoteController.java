package com.boommanpro.serverdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("note")
public class NoteController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("json")
    public JSONArray json() throws IOException{
        return JSON.parseArray(IOUtils.toString(applicationContext.getResource("classpath:note.json").getInputStream(), StandardCharsets.UTF_8));
    }
}
