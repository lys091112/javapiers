package com.xianyue.third.retrofit;

import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;

public class Main {

  public static void main(String[] args) throws IOException {

    Retrofit retrofit = BaseRetrofit.create("https://api.github.com/");
    SampleService service = retrofit.create(SampleService.class);
    Call<List<GithubRepo>> repos= service.getRepos("lys091112");

    System.out.println(repos.execute().body());

  }

}
