package pl.joble.infrastructure.offer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.joble.domain.offer.JobOfferFacade;
import pl.joble.domain.offer.dto.JobOfferDto;

import java.net.URI;
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
    ResponseEntity<JobOfferDto> saveOffer(@RequestBody @Valid RequestJobOfferDto dto){
        JobOfferDto dtoToSave = mapToDto(dto);
        JobOfferDto savedOffer = facade.saveOffer(dtoToSave);
        URI savedEntityLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOffer.id())
                .toUri();
        return ResponseEntity.created(savedEntityLocation).body(savedOffer);
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedOffer);
    }

    private JobOfferDto mapToDto(RequestJobOfferDto dto) {
        return JobOfferDto.builder()
                .title(dto.title())
                .companyName(dto.companyName())
                .salary(dto.salary())
                .url(dto.url())
                .description(dto.description())
                .build();
    }
}
