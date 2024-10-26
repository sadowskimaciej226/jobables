package pl.joble.infrastructure.offer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.joble.domain.offer.JobOfferFacade;
import pl.joble.domain.offer.dto.JobOfferDto;
import pl.joble.domain.offer.dto.ResponseJobOfferDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
class JobOfferController {
    private final JobOfferFacade facade;

    @GetMapping("/offer")
    ResponseEntity<List<JobOfferDto>> getOffer(){
        return ResponseEntity.ok(facade.findAllOffer());
    }
}
