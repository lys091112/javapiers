package com.xianyue.basictype.exception;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class BaseException extends RuntimeException {

    private String traceId;

    private ExceptionCode exceptionCode;

    public BaseException(ExceptionCode exceptionCode, String message) {
        super(message);
        this.traceId = UUID.randomUUID().toString();
        this.exceptionCode = exceptionCode;
    }

}
