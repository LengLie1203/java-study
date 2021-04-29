package com.lqw.patterns.shapes;

/**
 * @author LQW
 * @date 2021-04-09 19:08
 **/
// patterns/ShapeFactory2.java

import java.util.*;
import java.lang.reflect.*;

public class ShapeFactory2 implements FactoryMethod {
    Map<String, Constructor> factories = new HashMap<>();
    static Constructor load(String id) {
        System.out.println("loading " + id);
        try {
            return Class.forName("com.lqw.patterns.shapes." + id)
                    .getConstructor();
        } catch(ClassNotFoundException |
                NoSuchMethodException e) {
            throw new BadShapeCreation(id);
        }
    }
    public Shape create(String id) {
        try {
            return (Shape)factories
                    .computeIfAbsent(id, ShapeFactory2::load)
                    .newInstance();
        } catch(InstantiationException |
                IllegalAccessException |
                InvocationTargetException e) {
            throw new BadShapeCreation(id);
        }
    }
    public static void main(String[] args) {
        FactoryTest.test(new ShapeFactory2());
    }
}

