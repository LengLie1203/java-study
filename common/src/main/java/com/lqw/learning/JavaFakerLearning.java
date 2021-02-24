package com.lqw.learning;

import com.github.javafaker.Faker;

import java.util.Locale;

public class JavaFakerLearning {

    public static void main(String[] args) {
        Faker faker=new Faker(Locale.CHINA);

//        System.out.println(faker.expression());
//
//        System.out.println(faker.regexify());
//        System.out.println(faker.numerify("###"));
//        System.out.println(faker.letterify("????"));
//        System.out.println(faker.bothify("????",true));
        System.out.println(FakerTestEnum.values()[faker.random().nextInt(0,FakerTestEnum.values().length-1)].getValue());
//        List<Student> collect = Stream.generate(() -> {
//            Student student = new Student();
//            student.setName(faker.name().fullName());
//            student.setPercent(faker.number().randomDouble(0, 50, 100));
//            return student;
//        }).limit(100).collect(Collectors.toList());
//
//        collect.forEach(s->{
//            System.out.println(s.getName() +":"+ s.getPercent());
//        });
    }

}
