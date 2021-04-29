package com.lqw.patterns.shapes;

/**
 * @author LQW
 * @date 2021-04-09 19:12
 **/
public class BadShapeCreation extends RuntimeException {
    public BadShapeCreation(String msg) {
        super(msg);
    }
}
