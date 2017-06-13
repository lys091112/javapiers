package com.xianyue.util.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultMessage {
  @JsonProperty("ID")
  private Long ID;
  private String msg;
}
