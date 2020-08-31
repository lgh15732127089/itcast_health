package com.itheima.exceptionHandler;

import com.itheima.customExceptions.CustomException;
import com.itheima.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletException;

/**
 * @author : 光辉的mac
 * @ClassName MyException  处理异常的类
 * @date : 2020/8/28 17:12
 */
@RestControllerAdvice
@Slf4j//使用lombok的日志处理
public class MyException {

    //返回200
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomException.class)
    public Result handlerException(RuntimeException e){
        log.debug("出现了自定义异常"+ e);

        return new Result(false,"这是一个自定义异常"+e.getMessage());
    }

    @ExceptionHandler({ServletException.class,
                    HttpMessageConversionException.class,//http body转换异常，@RequestBody的参数
                    MethodArgumentNotValidException.class ,  //http请求缺少查询参数
                    MethodArgumentTypeMismatchException.class//http请求参数类型不匹配
            })
    public Result handlerOtherException(Exception e) throws Exception {
        log.debug("出现了其他异常");
        throw e;
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(
            Exception.class
    )
    public Result handlerAllException(Exception e){
        log.debug("所有的异常都可以抛出");
        return new Result(false,e.getMessage());
    }
}
