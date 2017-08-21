package com.xianyue.third.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class BaseRetrofit {

  private static Retrofit retrofit;

  public static Retrofit create(String baseUrl) {
    retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(JacksonConverterFactory.create())
        .build();
    return retrofit;
  }

}
