package cn.edu.ecnu.config;

import cn.edu.ecnu.exception.CustomizeException;
import cn.edu.ecnu.util.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class BackendExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(BackendExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)//统一处理指定类型异常，从而能够减少代码重复率和复杂度
    @ResponseBody
    public Object handleException(Exception e, HttpServletRequest request) {
        if (e instanceof CustomizeException) {
            CustomizeException customizeException = (CustomizeException) e;
            return ResultGenerator.genFreeResult(customizeException.getCode(), customizeException.getMessage(), null);
        } else {
            logger.error("[系统异常] {}", e);
            return ResultGenerator.genFailResult();
        }
    }
}
