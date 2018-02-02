package com.bocs.web;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


/**
 * Description:<p> 记录http的请求，参数的日志</p>
 * Created by songqi on 2017/4/9.
 */
@Aspect
@Component
public class LoggerAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut(value = "execution(* com.bocs.sys.controller.*.*(..))")
    public void log(){}

    @Before(value = "log()", argNames = "joinPoint")
    public void logBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url = {}",request.getRequestURL());
        logger.info("method = {}", request.getMethod());
        logger.info("remoteHost = {}", request.getRemoteHost());
        logger.info("port = {}", request.getRemotePort());
        logger.info("remoteAddr = {}", request.getRemoteAddr());
        logger.info("remoteUser = {}", request.getRemoteUser());
        logger.info("class.method = {}",joinPoint.getSignature().getDeclaringType() +"." + joinPoint.getSignature().getName());
        logger.info("args = {}", joinPoint.getArgs());
        Enumeration<String> enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            logger.info(paraName+": "+request.getParameter(paraName));
        }
    }

    @AfterReturning(value = "log()", returning = "object")
    public void logAfterReturning(Object object){
        logger.info("returning = {}", JSON.toJSON(object));
        logger.info("耗时(毫秒) = {}", (System.currentTimeMillis() - startTime.get()));
    }

}
