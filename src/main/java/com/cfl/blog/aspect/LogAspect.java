package com.cfl.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author Administer
 *
 * 日志记录类
 *
 *
 */
@Component
@Aspect
public class LogAspect {

    private final Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.cfl.blog.controller.*.*(..))")
    public void log(){

    }


    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求的地址
        String url  = request.getRequestURL().toString();
        // 获取请求的ip地址
        String ip = request.getRemoteAddr();
        // 获取请求执行的方法
        String classmethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 获取执行参数
        Object[] arge = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url,ip,classmethod,arge);

        logger.info("Result : {}",requestLog);
    }

    @After("log()")
    public void doAfter(){

    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("Result : {}",result);
    }



    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;


        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
