package com.BizEcho.shoppingplatform.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException() {
        super("Username already exists");
    }

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }

    // 你还可以添加其他构造器，接受原因异常或自定义错误信息等
}
