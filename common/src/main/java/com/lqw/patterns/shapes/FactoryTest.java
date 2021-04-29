package com.lqw.patterns.shapes;

import java.util.stream.*;

/**
 * @author LQW
 * @date 2021-04-09 18:59
 **/
// patterns/shapes/FactoryMethod.java
interface FactoryMethod {
    Shape create(String type);
}


public class FactoryTest {
    public static void test(FactoryMethod factory) {
        Stream.of("Circle", "Square", "Triangle",
                "Square", "Circle", "Circle", "Triangle")
                .map(factory::create)
                .peek(Shape::draw)
                .peek(Shape::erase)
                .count(); // Terminal operation
    }
}
