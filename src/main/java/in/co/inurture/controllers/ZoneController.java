package in.co.inurture.controllers;

import in.co.inurture.dtos.ApiResponseMessage;
import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.dtos.UniversityDto;
import in.co.inurture.dtos.ZoneDto;
import in.co.inurture.entities.University;
import in.co.inurture.services.UniversityService;
import in.co.inurture.services.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private UniversityService universityService;

    //create
    @PostMapping
    public ResponseEntity<ZoneDto> createZone(@Valid @RequestBody ZoneDto zoneDto) {
        //call service to   save object
        ZoneDto zoneDto1 = zoneService.create(zoneDto);
        return new ResponseEntity<>(zoneDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{zoneId}")
    public ResponseEntity<ZoneDto> updateZone(
            @PathVariable String zoneId,
            @RequestBody ZoneDto zoneDto
    ) {
        ZoneDto updatedZone = zoneService.update(zoneDto, zoneId);
        return new ResponseEntity<>(updatedZone, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{zoneId}")
    public ResponseEntity<ApiResponseMessage> deleteZone(
            @PathVariable String zoneId
    ) {
        zoneService.delete(zoneId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Zone is deleted successfully !!").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);


    }

    //get all

    @GetMapping
    public ResponseEntity<PageableResponse<ZoneDto>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir


    ) throws InterruptedException {
        //  Thread.sleep(1000);
        PageableResponse<ZoneDto> pageableResponse = zoneService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }

    //get single
    @GetMapping("/{zoneId}")
    public ResponseEntity<ZoneDto> getSingle(@PathVariable String zoneId) {
        ZoneDto zoneDto = zoneService.get(zoneId);
        return ResponseEntity.ok(zoneDto);
    }

    //create Universities with Zone
    @PostMapping("/{zoneId}/universities")
    public ResponseEntity<UniversityDto> createUniversitiesWithZone(
            @PathVariable("zoneId") String zoneId,
            @RequestBody UniversityDto dto
    ) {
        UniversityDto universityWithZone = universityService.createWithZone(dto, zoneId);
        return new ResponseEntity<>(universityWithZone, HttpStatus.CREATED);
    }

    //update Zone of University
    @PutMapping("/{zoneId}/universities/{universityId}")
    public ResponseEntity<UniversityDto> updateZoneOfUniversity(
            @PathVariable String zoneId,
            @PathVariable String universityId
    ) {
        UniversityDto universityDto = universityService.updateZone(universityId, zoneId);
        return new ResponseEntity<>(universityDto, HttpStatus.OK);
    }

    //get Zone of Universities
    @GetMapping("/{zoneId}/universities")
    public ResponseEntity<PageableResponse<UniversityDto>> getUniversityOfZone(
            @PathVariable String zoneId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {

        PageableResponse<UniversityDto> response = universityService.getAllOfZone(zoneId,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}

