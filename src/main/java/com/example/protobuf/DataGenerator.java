package com.example.protobuf;

import com.example.protobuf.vo.Person;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    public static List<Person> generatePersonList(int numberOfPersons) {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < numberOfPersons; i++) {
            personList.add(new Person("Person " + i, i, i % 2 == 0));
        }
        return personList;
    }

}
