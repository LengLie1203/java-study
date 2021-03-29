package com.lqw.init;

/**
* @author LQW
* @date 2021-03-23 09:48
**/
public class InitP{
   static {
       System.out.println("initp1");
   }

   {
       System.out.println("initp2");
   }

   public InitP() {

       System.out.println("initp3");
   }
}
