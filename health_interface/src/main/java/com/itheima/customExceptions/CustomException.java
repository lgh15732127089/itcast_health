package com.itheima.customExceptions;

/**
 * @author : 光辉的mac
 * @ClassName CustomException 自定义异常
 * @date : 2020/8/28 17:10
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}
