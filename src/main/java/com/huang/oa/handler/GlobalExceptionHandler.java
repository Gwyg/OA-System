package com.huang.oa.handler;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import com.huang.oa.exception.PasswordErrorException;
import com.huang.oa.exception.UsernameErrorException;
import com.huang.oa.exception.UsernameExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameExistsException.class)
    public SaResult usernameExistsException(UsernameExistsException e) {
        return SaResult.error(e.getMessage());
    }
    @ExceptionHandler(UsernameErrorException.class)
    public SaResult usernameErrorException(UsernameErrorException e) {
        return SaResult.error(e.getMessage());
    }
    @ExceptionHandler(PasswordErrorException.class)
    public SaResult passwordErrorException(PasswordErrorException e) {
        return SaResult.error(e.getMessage());
    }
    @ExceptionHandler(NotLoginException.class)
    public SaResult notLoginException(NotLoginException e) {
        return SaResult.error(e.getMessage());
    }
}
