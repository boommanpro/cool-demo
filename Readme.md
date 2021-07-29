# Cool-Demo

cool-demo是一个maven插件的入门教程、利用javaparser-core的parse能力及maven的生命周期能力。
1. 读取.java文件的注释内容、并处理。
2. 在maven-initialize阶段将内容写入到项目的resource目录下。

这样就可以将代码中的注释通过项目能力做自己想要的事，并且和maven的构建结合起来，有效的和CI/CD结合。

## 最终效果

执行server-demo的maven spring-boot:run后、可以在http://localhost:8081/note/json 接口查看注释信息

## 关联内容

1. maven插件执行的阶段 org.apache.maven.plugins.annotations.LifecyclePhase