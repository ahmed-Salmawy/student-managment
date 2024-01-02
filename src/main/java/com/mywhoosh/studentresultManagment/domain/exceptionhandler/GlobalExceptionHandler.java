package com.mywhoosh.studentresultManagment.domain.exceptionhandler;

import com.mywhoosh.studentresultManagment.domain.dto.MessageResponseDto;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

   // @ExceptionHandler(MethodArgumentNotValidException.class)
   @MessageExceptionHandler(MethodArgumentNotValidException.class)
   @SendTo("/topic/students")
   public MessageResponseDto handleValidationException(MethodArgumentNotValidException ex) {
       // Log or handle the validation exception as needed
       // You can access the validation errors using ex.getBindingResult()

       List<String> errorMessages = ex.getBindingResult()
               .getFieldErrors()
               .stream()
               .map(error -> error.getDefaultMessage())
               .collect(Collectors.toList());

       return new MessageResponseDto("Validation failed".concat(errorMessages.toString()));
   }

   /* @MessageExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        // Log or handle the exception as needed
        return "Error occurred: " + ex.getMessage();
    }*/
}