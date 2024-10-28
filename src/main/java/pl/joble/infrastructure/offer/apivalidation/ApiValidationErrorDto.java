package pl.joble.infrastructure.offer.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiValidationErrorDto(List<String> errors, HttpStatus httpStatus) {
}
