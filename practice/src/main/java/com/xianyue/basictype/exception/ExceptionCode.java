package com.xianyue.basictype.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCode {

  /** 自定义的test异常码 */
  TEST(10001, "test exception");

  private int codeId;
  private String message;
}
