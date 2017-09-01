package com.xianyue.basictype.exception;

public class TestException extends BaseException {

  public TestException(ExceptionCode exceptionCode, String message) {
    super(exceptionCode, message);
  }

  public TestException(String message) {
    super(ExceptionCode.TEST, message);
  }
}
