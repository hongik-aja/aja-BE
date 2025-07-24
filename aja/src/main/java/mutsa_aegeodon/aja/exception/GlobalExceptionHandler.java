package mutsa_aegeodon.aja.exception;

import mutsa_aegeodon.aja.dto.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorCode code = ex.getErrorCode();
        ErrorResponse body = new ErrorResponse(code.getCode(), code.getMessage());
        return ResponseEntity
                .status(code.getStatus())
                .body(body);
    }
}

