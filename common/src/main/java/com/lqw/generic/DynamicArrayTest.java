package com.lqw.generic;

import java.lang.reflect.Array;

/**
 * @author LQW
 * @date 2021-03-25 21:30
 **/

public class DynamicArrayTest {

    private static Object resizeArray (Object oldArray, int newSize) {
        //获取数组oldArray的长度
        int oldSize = Array.getLength(oldArray);
        //获取数组oldArray的元素类型
        Class elementType = oldArray.getClass().getComponentType();
        //实例一个新的数组 类型和oldArray的一样 长度参数传入的newSize
        Object newArray = Array.newInstance(elementType,newSize);
        //得到新数组newArray 和oldArray两个中长度最短的，并把长度返回给preserveLength
        int preserveLength = Math.min(oldSize,newSize);
        //数组内容复制
        if (preserveLength > 0) {
            System.arraycopy (oldArray, 0, newArray, 0, preserveLength);
        }
        return newArray;
    }

    public static final void main(String... args) throws Exception {
//        int[] a = {1,2,3};
//        a = (int[])resizeArray(a,5);
//        a[3] = 4;
//        a[4] = 5;
//        for (int i=0; i<a.length; i++) {
//            System.out.println (a[i]);
//        }


        int[] o = (int[]) Array.newInstance(int.class, 3);
        for (int i : o) {
            System.out.println(i);
        }
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.getClass().getName()+System.nanoTime();
    }
}
