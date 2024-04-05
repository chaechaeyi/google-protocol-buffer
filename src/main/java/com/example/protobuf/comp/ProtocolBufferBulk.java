package com.example.protobuf.comp;

import example.Example.Person;
import example.Example.People;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProtocolBufferBulk {
    public static void main(String[] args) throws Exception {
        People.Builder peopleBuilder = People.newBuilder();

        // 대량의 데이터 추가
        for (int i = 0; i < 10000; i++) { // 예시로 1만 개의 Person 객체 생성
            Person person = Person.newBuilder()
                    .setName("Person " + i)
                    .setId(i)
                    .setEmail(false)
                    .build();
            peopleBuilder.addPeople(person);
        }

        People people = peopleBuilder.build();

        // 메모리 사용량 측정 시작
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // 직렬화 시작 시간
        long startTime = System.nanoTime();
        byte[] serializedData = people.toByteArray();
        long endTime = System.nanoTime();

        // 역직렬화 시작 시간
        long startTimeDes = System.nanoTime();
        People deserializedPeople = People.parseFrom(serializedData);
        long endTimeDes = System.nanoTime();

        // 직렬화 & 역직렬화 후 메모리 사용량
        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long usedMemory = afterMemory - beforeMemory;

        log.info("protobuf 직렬화 시간: {} ns", endTime - startTime);
        log.info("protobuf 역직렬화 시간: {} ns", endTimeDes - startTimeDes );
        log.info("protobuf 데이터 크기: {} 바이트", serializedData.length );
        log.info("직렬화에 소모한 JSON 메모리 사용량: {} 바이트", usedMemory);
    }

}
