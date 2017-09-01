package com.xianyue.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JackSonMain {

  public static void main(String[] args) throws IOException {
      objectMapperTest();
  }


  public static void objectMapperTest() throws IOException {
    String str = "{\"ID\":1,\"msg\":\"love daisy\"}";
    ObjectMapper objectMapper = new ObjectMapper();
//    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    ResultMessage res = objectMapper.readValue(str, ResultMessage.class);

    System.out.println(res.getID() + res.getMsg());

  }



}
