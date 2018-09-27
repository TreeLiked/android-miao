package com.ywdnf.androidmiao.global;

import lombok.NoArgsConstructor;
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
 * @author lqs2
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.ywdnf.androidmiao.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 拦截请求参数信息
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        logger.info("Remote IP: " + request.getRemoteAddr());
        logger.info("Request URL: " + request.getRequestURL().toString());
        logger.info("Request METHOD: " + request.getMethod());
        Enumeration<String> enu = request.getParameterNames();
        int i = 1;
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            String value = request.getParameter(name);
            if (value.length() <= 100) {
                logger.info("Param[{}]: name= {}, value= {}", i++, name, value);
            } else {
                logger.info("Param[{}]: name= {}, value= {}", i++, name, value.substring(0, 97));
            }
        }
    }

    @AfterReturning(returning = "resp", pointcut = "webLog()")
    public void doAfterReturning(Object resp) {

        if (resp instanceof String) {
            String value = (String) resp;
            if (value.length() > 1000) {
                logger.info("Response: " + value.substring(0, 97) + "...");
            } else {
                logger.info("Response: " + resp);

            }
        }
    }
}
