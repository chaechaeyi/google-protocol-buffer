package com.example.protobuf.comp;

import com.example.protobuf.vo.Person;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class XmlBulk {
    public static void main(String[] args) throws Exception {
        // 데이터 준비
        List<Person> personList = new ArrayList<>();
        // 10,000개의 Person 객체 생성 및 추가
        for (int i = 0; i < 10000; i++) {
            personList.add(new Person("Person " + i, i, i % 2 == 0));
        }

        XmlMapper xmlMapper = new XmlMapper();

        // 직렬화 전 메모리 사용량
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // 직렬화
        long start = System.nanoTime();
        String xmlData = xmlMapper.writeValueAsString(personList);
        long serializationTime = System.nanoTime() - start;

        // 역직렬화
        start = System.nanoTime();
        List<Person> deserializedList = xmlMapper.readValue(xmlData, xmlMapper.getTypeFactory().constructCollectionType(List.class, Person.class));
        long deserializationTime = System.nanoTime() - start;

        // 직렬화 & 역직렬화 후 메모리 사용량
        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long usedMemory = afterMemory - beforeMemory;

        // 결과 출력
        log.info("XML 직렬화 시간: {} ns", serializationTime );
        log.info("XML 역직렬화 시간: {} ns", deserializationTime );
        log.info("XML 데이터 크기: {} 바이트", xmlData.getBytes().length );
        log.info("직렬화에 소모한 JSON 메모리 사용량: {} 바이트", usedMemory);
    }
}
