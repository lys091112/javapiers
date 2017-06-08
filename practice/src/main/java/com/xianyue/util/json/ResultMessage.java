package com.xianyue.util.json;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultMessage {
  private Long ID;
  private String msg;
}
