package com.lqw.generic;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LQW
 * @date 2021-03-28 22:23
 **/
public class NeedCasting {
    //    @SuppressWarnings("unchecked")
    public void f(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(args[0]));
        List<Widget> shapes = (List<Widget>) in.readObject();

        List<Widget> lw2 = List.class.cast(in.readObject());

        List<Widget> lw3= (List<Widget>)List.class.cast(in.readObject());
    }
}


class Widget {

}