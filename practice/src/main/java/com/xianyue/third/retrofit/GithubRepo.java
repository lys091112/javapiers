package com.xianyue.third.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepo {

  private int id;
  private String name;

  @Override
  public String toString() {
    return "GithubRepo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}' + "\n";
  }
}
