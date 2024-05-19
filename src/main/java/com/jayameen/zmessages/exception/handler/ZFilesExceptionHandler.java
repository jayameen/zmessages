package com.jayameen.zmessages.exception.handler;

import com.jayameen.zmessages.dto.AppResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class ZFilesExceptionHandler {

    @Value("${spring.servlet.multipart.max-file-size:10}")
    private Integer allowedFileSize;

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public ResponseEntity<?> maxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
        AppResponse appResponse = new AppResponse();
        appResponse.setStatus("error");
        appResponse.setDescription("File size exceeds the limit of "+ (allowedFileSize)/(1000000) +" MB");
        return ResponseEntity.badRequest().body(appResponse);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(IllegalArgumentException exception) {
        AppResponse appResponse = new AppResponse();
        appResponse.setStatus("error");
        appResponse.setDescription(exception.getMessage());
        return ResponseEntity.badRequest().body(appResponse);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<?> nullPointerException(NullPointerException exception) {
        AppResponse appResponse = new AppResponse();
        appResponse.setStatus("error");
        appResponse.setDescription(exception.getCause().toString());
        return ResponseEntity.internalServerError().body(appResponse);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exception(Exception exception) {
        exception.printStackTrace();
        AppResponse appResponse = new AppResponse();
        appResponse.setStatus("error");
        appResponse.setDescription(exception.getMessage());
        return ResponseEntity.internalServerError().body(appResponse);
    }


}
