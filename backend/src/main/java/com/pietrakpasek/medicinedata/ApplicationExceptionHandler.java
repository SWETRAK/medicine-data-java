package com.pietrakpasek.medicinedata;

import com.pietrakpasek.medicinedata.exceptions.EmailExistsException;
import com.pietrakpasek.medicinedata.exceptions.EmptyPageException;
import com.pietrakpasek.medicinedata.exceptions.ErrorResponse;
import com.pietrakpasek.medicinedata.exceptions.InvalidJwtException;
import com.pietrakpasek.medicinedata.exceptions.NotMatchingPasswordException;
import com.pietrakpasek.medicinedata.exceptions.RoleNotFoundException;
import lombok.NonNull;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({PropertyReferenceException.class})
    private ResponseEntity<ErrorResponse> handlePropertyReferenceException(PropertyReferenceException e){
        ErrorResponse error = new ErrorResponse(List.of(e.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotMatchingPasswordException.class})
    private ResponseEntity<ErrorResponse> handleNotMatchingPasswordException(NotMatchingPasswordException e){
        ErrorResponse error = new ErrorResponse(List.of(e.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RoleNotFoundException.class})
    private ResponseEntity<ErrorResponse> handleRoleNotFoundException(RoleNotFoundException e){
        ErrorResponse error = new ErrorResponse(List.of(e.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidJwtException.class})
    private ResponseEntity<ErrorResponse> handleInvalidJwtException(InvalidJwtException e) {
        ErrorResponse error = new ErrorResponse(List.of(e.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({EmptyPageException.class})
    private ResponseEntity<ErrorResponse> handleEmptyPageException(RuntimeException e) {
        ErrorResponse error = new ErrorResponse(List.of(e.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EmailExistsException.class})
    private ResponseEntity<ErrorResponse> handleEmailExistsException(EmailExistsException e) {
        ErrorResponse error = new ErrorResponse(List.of(e.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NullPointerException.class})
    private ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e) {
        ErrorResponse error = new ErrorResponse(List.of(e.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }
}
