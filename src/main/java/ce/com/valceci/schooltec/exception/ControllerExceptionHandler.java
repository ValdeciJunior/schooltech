package ce.com.valceci.schooltec.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ce.com.valceci.schooltec.exception.model.AttributeMessage;
import ce.com.valceci.schooltec.exception.model.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
	
	@ExceptionHandler(DisciplinaNotFound.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> disciplinaNotFound(DisciplinaNotFound e) {
        log.error("DisciplinaNotFound");
        ExceptionResponse err = new ExceptionResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
	
	@ExceptionHandler(FileUploadException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> fileUploadException(FileUploadException e) {
        log.error("FileUploadException");
        List<AttributeMessage> attributeMessages = new ArrayList<>();
        ExceptionResponse err = new ExceptionResponse(HttpStatus.EXPECTATION_FAILED, e.getMessage(), attributeMessages);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(err);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException");
        List<AttributeMessage> attributeMessages = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            attributeMessages.add(new AttributeMessage(fieldName, errorMessage));
        });
        ExceptionResponse err = new ExceptionResponse(HttpStatus.BAD_REQUEST, "Falha de validação.", attributeMessages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
