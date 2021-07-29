package com.boommanpro.cooldemo.utils;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class MethodUtils {

    private MethodUtils() {
    }

    public static void getAllMethod(List<MethodDeclaration> all, Node parse) {
        if (parse != null) {
            parse.getChildNodes().forEach(node -> {
                getAllMethod(all, node);
                if (node instanceof MethodDeclaration) {
                    all.add((MethodDeclaration) node);
                }
            });
        }
    }
}
