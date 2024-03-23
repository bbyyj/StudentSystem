package com.example.backend.handler;


import com.example.backend.constants.HttpStatus;
import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultBean<Void> exceptionHandler(Exception e){
        if(e instanceof GlobalException exception){
            return ResultBean.error(exception.getCode(), exception.getMessage());
        }
        if (e instanceof ConstraintViolationException exception)
            return ResultBean.error(HttpStatus.HTTP_STATUS_422, exception.getMessage());
        if (e instanceof MethodArgumentNotValidException exception)
            if (exception.getFieldError() != null)
                return ResultBean.error(HttpStatus.HTTP_STATUS_422, exception.getFieldError().getDefaultMessage());
        if (e instanceof BindException exception)
            return ResultBean.error(HttpStatus.HTTP_STATUS_422, exception.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpMessage.SYSTEM_ERROR);

    }
}
