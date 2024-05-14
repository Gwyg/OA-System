package com.huang.oa.exception;

import com.fasterxml.jackson.databind.ser.Serializers;

public class UsernameErrorException extends BaseException {

    public UsernameErrorException() {}

    public UsernameErrorException(String msg) {super(msg);}

}
