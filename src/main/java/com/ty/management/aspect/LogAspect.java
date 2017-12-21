package com.ty.management.aspect;

import com.ty.management.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.ty.management.controller.*Controller.*(..))")
    public void controllerAspect(){

    }

    @Before("controllerAspect()")
    public void doBefore(JoinPoint  joinPoint){
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning="obj", pointcut="controllerAspect()")
    public void after(Object obj) throws Throwable{
        logger.info("RESPONSE:" + obj.toString());
    }

    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint joinPoint)throws Throwable{
        Object returnVal = joinPoint.proceed();
        long start = System.currentTimeMillis();
        try{
            joinPoint.proceed();
            long end = System.currentTimeMillis();
            logger.info("around" + joinPoint + "user time :" + (end-start) + "ms!");
        }catch (Throwable e){
            long end = System.currentTimeMillis();
            logger.info("around" + joinPoint + "user time with exception:" + (end-start) + "ms!");
        }
        //一定要有这步，否则无法正常运行
        //返回对象
        return returnVal;
    }

    @AfterThrowing(value = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint,Throwable e) throws ClassNotFoundException {
        //待完善
        String params = "";
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
                params += joinPoint.getArgs()[i] + ";";
            }
        }
        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);
    }

}
