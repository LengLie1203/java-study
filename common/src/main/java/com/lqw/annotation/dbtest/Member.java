package com.lqw.annotation.dbtest;

/**
 * @author LQW
 * @date 2021-03-30 23:03
 **/
@DBTable(name = "MEMBER")
public class Member {

   private @SQLString(value = 30) String firstName;

   @SQLString(value = 50)
   private String lastName;

   @SQLInteger
   private Integer age;
    @SQLString(value = 30,
            constraints = @Constraints(primaryKey = true))
   private String reference;

   private Integer memberCount;



}
