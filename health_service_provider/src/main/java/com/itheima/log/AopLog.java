package com.itheima.log;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : 光辉的mac  日志处理
 * @ClassName AopLog
 * @date : 2020/9/4 10:27
 */
@Aspect
@Component
@Slf4j
public class AopLog {


    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    public void aspectLog(){

    }

    //环绕通知
    @Around("aspectLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        //这个是controller中可以使用这中方式获取请求中的参数
        //RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        //ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        //HttpServletRequest request = sra.getRequest();
        //String url = request.getRequestURL().toString();
        //String method = request.getMethod();
        //String uri = request.getRequestURI();
        //String queryString = request.getQueryString();//request自己的遍历参数方法,通过&连接
        //log.debug("请求开始, 各个参数, url: {}", url);
        //log.debug("请求开始======>>>>>>>,  uri: {}", uri);
        //log.debug("请求开始======>>>>>>>,  method: {}", method);
        //logger.info("请求开始,  params: {}", queryString);
        // result的值就是被拦截方法的返回值

        Object result = pjp.proceed();
        log.debug("请求的service方法是<<<<<<========="+pjp.getSignature().getName());
        log.debug("请求的service参数类型是<<<<<<========="+pjp.getArgs());
        log.debug("请求结束，SERVICE的返回值是 <<<<<<<<=======" + JSONArray.toJSONString(result));
        return result;
    }
}
