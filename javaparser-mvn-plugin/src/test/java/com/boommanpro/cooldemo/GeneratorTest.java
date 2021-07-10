package com.boommanpro.cooldemo;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class GeneratorTest {

    public static String baseDir = "D:\\java_work_space\\cool-demo\\server-demo";

    @Test
    public void generatorTest() throws FileNotFoundException {
        String targetFile = "com.boommanpro.serverdemo.CommonUtils";

        JavaParser parser = new JavaParser();
        CompilationUnit parse = JavaParser.parse(new File(getAbsolutePath(targetFile)));

        System.out.println(123);
    }

    private String getAbsolutePath(String fileName){
        return String.format("%s\\src\\main\\java\\%s.java", baseDir, fileName.replace(".", "\\\\"));
    }
}
