package pl.joble.infrastructure.offer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.joble.domain.offer.JobOfferFacade;
import pl.joble.domain.offer.dto.JobOfferDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/offer")
class JobOfferController {
    private final JobOfferFacade facade;

    @GetMapping
    ResponseEntity<List<JobOfferDto>> getOffer(){
        return ResponseEntity.ok(facade.findAllOffer());
    }
    @GetMapping("{id}")
    ResponseEntity<JobOfferDto> getOfferById(@PathVariable String id){
        return ResponseEntity.ok(facade.findOfferById(id));
    }
    @PostMapping()
    ResponseEntity<JobOfferDto> saveOffer(@RequestBody @Valid JobOfferDto dto){
        JobOfferDto savedOffer = facade.saveOffer(dto);
        return ResponseEntity.ok(savedOffer);
    }
}
