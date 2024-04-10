package com.example.protobuf.comp;

import com.example.protobuf.DataGenerator;
import com.example.protobuf.vo.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JsonBulk {
    public static void main(String[] args) throws Exception {
        // 데이터 준비
        List<Person> personList = DataGenerator.generatePersonList(10000); // 1만 개의 Person 객체 생성


        ObjectMapper mapper = new ObjectMapper();

        // 직렬화 전 메모리 사용량
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        // 직렬화
        long start = System.nanoTime();
        String jsonData = mapper.writeValueAsString(personList);
        long serializationTime = System.nanoTime() - start;

        // 직렬화 후 메모리 사용량


        // 역직렬화
        start = System.nanoTime();
        List<Person> deserializedPersonList = mapper.readValue(jsonData, new TypeReference<List<Person>>(){});
        long deserializationTime = System.nanoTime() - start;

        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long usedMemory = afterMemory - beforeMemory;

        // 결과 출력
        log.info("JSON 직렬화 시간: {} ns", serializationTime );
        log.info("JSON 역직렬화 시간: {} ns", deserializationTime );
        log.info("JSON 데이터 크기: {} 바이트", jsonData.getBytes().length );
        log.info("직렬화에 소모한 JSON 메모리 사용량: {} 바이트", usedMemory);
    }

}
