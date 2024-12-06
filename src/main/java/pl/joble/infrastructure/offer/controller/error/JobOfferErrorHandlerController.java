package pl.joble.infrastructure.offer.controller.error;

import com.mongodb.DuplicateKeyException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.joble.domain.offer.BadParametersException;
import pl.joble.domain.offer.JobOfferNotFoundException;

@ControllerAdvice
@Log4j2
public class JobOfferErrorHandlerController {

    @ExceptionHandler(JobOfferNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public JobOfferErrorResponse handleNotFoundError(JobOfferNotFoundException e){
        String message = e.getMessage();
        log.error(message);
        return new JobOfferErrorResponse(message, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(BadParametersException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JobOfferErrorResponse handleNotBadParameter(BadParametersException e){
        String message = e.getMessage();
        log.error(message);
        return new JobOfferErrorResponse(message, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public JobOfferErrorResponse handleNotUniqueIds(DuplicateKeyException e){
        String message = e.getMessage();
        log.error(message);
        return new JobOfferErrorResponse(message, HttpStatus.CONFLICT);
    }
}
