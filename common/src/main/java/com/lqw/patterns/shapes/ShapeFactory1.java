package com.lqw.patterns.shapes;

/**
 * @author LQW
 * @date 2021-04-09 19:08
 **/
public class ShapeFactory1 implements FactoryMethod {
    public Shape create(String type) {
        switch (type) {
            case "Circle":
                return new Circle();
            case "Square":
                return new Square();
            case "Triangle":
                return new Triangle();
            default:
                throw new BadShapeCreation(type);
        }
    }

    public static void main(String[] args) {
        FactoryTest.test(new ShapeFactory1());
    }
}