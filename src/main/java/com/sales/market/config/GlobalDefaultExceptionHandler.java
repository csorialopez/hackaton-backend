package com.sales.market.config;

import com.sales.market.dto.OperationResultDto;
import com.sales.market.exception.NotFoundException;
import com.sales.market.exception.purchases.GenericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.persistence.NoResultException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    public static final String ERROR_PROCESSING_REQUEST = "No preview available";

    @ExceptionHandler(value = { GenericException.class})
    public ResponseEntity<String> handleGenericException (GenericException genericException) {
        return new ResponseEntity<String>(genericException.getMessage(), genericException.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e) {
        logger.error("Internal server error", e);
        String detailMessage = e.getMessage();
        if (detailMessage == null || detailMessage.trim().isEmpty()) {
            return new ResponseEntity<>(new OperationResultDto<>("messages.genericExceptions.internalServerError"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(detailMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public final ResponseEntity<Object> handleMaxUploadSizeExceptions(Exception e) {
        return new ResponseEntity<>(new OperationResultDto<>("messages.genericExceptions.maxUploadSizeExceeded"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResultException.class)
    public final ResponseEntity<Object> handleNoResultExceptions(Exception e) {
        String detailMessage = e.getMessage();
        if (detailMessage == null || detailMessage.trim().isEmpty()) {
            return new ResponseEntity<>(new OperationResultDto<>("messages.genericExceptions.noResult"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(detailMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<String> handleNotFoundExceptions(Exception e) {
        return new ResponseEntity<>(ERROR_PROCESSING_REQUEST, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public final ResponseEntity<String> handleObjectOptimisticLockingFailureExceptions(Exception e) {
        logger.error("error de concurrencia ", e);
        return new ResponseEntity<>("It seems that another user could have modified the values before you, please try" +
                " again refreshing the browser", HttpStatus.CONFLICT);
    }
}
