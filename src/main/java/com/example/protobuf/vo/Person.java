package com.example.protobuf.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Person {
    private String name;
    private int id;
    private boolean isActive;

    public Person() { }

    public Person(String name, int id, boolean isActive) {
        this.name = name;
        this.id = id;
        this.isActive = isActive;
    }

}
