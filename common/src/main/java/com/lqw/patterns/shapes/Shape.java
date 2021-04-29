package com.lqw.patterns.shapes;

/**
 * @author LQW
 * @date 2021-04-09 18:57
 **/
// patterns/shapes/BadShapeCreation.java package patterns.shapes;


// patterns/shapes/Shape.java
public class Shape {
    private static int counter = 0;
    private int id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + id + "]";
    }

    public void draw() {
        System.out.println(this + " draw");
    }

    public void erase() {
        System.out.println(this + " erase");
    }
}

// patterns/shapes/Circle.java


