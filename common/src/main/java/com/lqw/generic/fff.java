package com.lqw.generic;

/**
 * @author LQW
 * @date 2021-03-28 22:14
 **/
interface Payable<T> {
}

class Employee implements Payable<Employee> {
}

//class Hourly extends Employee implements Payable<Hourly> {
//}