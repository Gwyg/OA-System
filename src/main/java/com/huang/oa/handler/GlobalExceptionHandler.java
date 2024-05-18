package com.huang.oa.handler;


import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.util.SaResult;
import com.huang.oa.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameExistsException.class)
    public SaResult usernameExistsException(UsernameExistsException e) {return SaResult.error(e.getMessage());}

    @ExceptionHandler(UsernameErrorException.class)
    public SaResult usernameErrorException(UsernameErrorException e) {return SaResult.error(e.getMessage());}

    @ExceptionHandler(PasswordErrorException.class)
    public SaResult passwordErrorException(PasswordErrorException e) {return SaResult.error(e.getMessage());}

    @ExceptionHandler(NotLoginException.class)
    public SaResult notLoginException(NotLoginException e) {return SaResult.error(e.getMessage());}

    @ExceptionHandler(NotOneDepartmentException.class)
    public SaResult notOneDepartmentException(NotOneDepartmentException e) {return SaResult.error(e.getMessage());}

    @ExceptionHandler(InsertFailureException.class)
    public SaResult insertFailureException(InsertFailureException e) {return SaResult.error(e.getMessage());}

    @ExceptionHandler(AlreadyExistException.class)
    public SaResult workGroupAlreadyExistException(AlreadyExistException e) {return SaResult.error(e.getMessage());}

    @ExceptionHandler(LackOfAuthorityException.class)
    public SaResult lackOfAuthorityException(LackOfAuthorityException e) {return SaResult.error(e.getMessage());}

    @ExceptionHandler(NotRoleException.class)
    public SaResult notRoleException(NotRoleException e) {return SaResult.error(e.getMessage());}

    @ExceptionHandler(NotPermissionException.class)
    public SaResult notPermissionException(NotPermissionException e) {return SaResult.error(e.getMessage());}

    @ExceptionHandler(NotModifiedException.class)
    public SaResult notModifiedException(NotModifiedException e) {return SaResult.error(e.getMessage());}
}
