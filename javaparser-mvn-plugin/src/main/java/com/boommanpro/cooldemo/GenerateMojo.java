package com.boommanpro.cooldemo;


import com.alibaba.fastjson.JSON;
import com.boommanpro.cooldemo.model.JavaDoc;
import com.boommanpro.cooldemo.utils.MethodUtils;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Mojo(name = "generate", defaultPhase = LifecyclePhase.INITIALIZE, threadSafe = true)
public class GenerateMojo extends AbstractMojo {
    /**
     *
     */
    @Parameter(name = "targetFile")
    private String targetFile;

    @Parameter(defaultValue = "${project.basedir}", readonly = true)
    private File basedir;

    @Override
    @SuppressWarnings("all")
    public void execute()
            throws MojoExecutionException {
        if (targetFile == null || targetFile.equals("")) {
            getLog().error("targetFile文件未配置");
            return;
        }
        getLog().info(String.format("生成JavaParser文件开始,targetFile:%s,baseDir:%s", targetFile, basedir.getPath()));
        ParserConfiguration configuration = new ParserConfiguration();
        JavaParser javaParser = new JavaParser(configuration);
        try {
            ParseResult<CompilationUnit> parse = javaParser.parse(new File(getAbsolutePath(targetFile)));
            List<MethodDeclaration> all = new ArrayList<>();
            MethodUtils.getAllMethod(all, parse.getResult().get());
            List<JavaDoc> docList = all
                    .stream()
                    .map(method -> new JavaDoc(method.getComment().get().getContent()))
                    .collect(Collectors.toList());
            FileOutputStream fileOutputStream = new FileOutputStream(getResourcePath() + "/note.json");
            fileOutputStream.write(JSON.toJSONBytes(docList));
            fileOutputStream.close();
        } catch (IOException e) {
            getLog().error("生成JSON文件异常:", e);
        }
    }

    private String getAbsolutePath(String fileName) {
        return String.format("%s/src/main/java/%s.java", basedir, fileName.replace(".", File.separator));
    }

    private String getResourcePath() {
        return String.format("%s/src/main/resources", basedir);
    }
}
