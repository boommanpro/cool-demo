package com.boommanpro.cooldemo;


import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Mojo(name = "generate",defaultPhase = LifecyclePhase.PREPARE_PACKAGE,threadSafe = true)
public class GenerateMojo extends AbstractMojo
{
    /**
     *
     */
    @Parameter(name = "targetFile")
    private String targetFile;

    @Parameter(defaultValue = "${project.basedir}", readonly = true)
    private File basedir;

    public void execute()
        throws MojoExecutionException
    {
        if (targetFile==null||targetFile.equals("")) {
            getLog().error("targetFile文件未配置");
            return;
        }
        getLog().info(String.format("生成JavaParser文件开始,targetFile:%s,baseDir:%s",targetFile,basedir.getPath()));


        try {
            CompilationUnit parse = JavaParser.parse(new File(getAbsolutePath(targetFile)));
            System.out.println(parse.getClass());

        }catch (Exception e){
            e.printStackTrace();
        }


        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getResourcePath() + "\\a.json");
            fileOutputStream.write(new String("123456").getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String getAbsolutePath(String fileName){
        return String.format("%s\\src\\main\\java\\%s.java", basedir, fileName.replace(".", "\\\\"));
    }

    private String getResourcePath(){
        return String.format("%s\\src\\main\\resources", basedir);
    }
}
