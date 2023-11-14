package in.co.inurture.controllers;

import in.co.inurture.dtos.ApiResponseMessage;
import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.dtos.UniversityDto;
import in.co.inurture.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/universities")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    //create
    @PostMapping
    public ResponseEntity<UniversityDto> createUniversity(@RequestBody UniversityDto universityDto) {
        UniversityDto createdUniversity = universityService.create(universityDto);
        return new ResponseEntity<>(createdUniversity, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{universityId}")
    public ResponseEntity<UniversityDto> updateUniversity(@PathVariable String universityId, @RequestBody UniversityDto universityDto) {
        UniversityDto updatedUniversity = universityService.update(universityDto, universityId);
        return new ResponseEntity<>(updatedUniversity, HttpStatus.OK);
    }


    //delete
    @DeleteMapping("/{universityId}")
    public ResponseEntity<ApiResponseMessage> delete(@PathVariable String universityId) {
        universityService.delete(universityId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("University is deleted successfully !!").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    //get single

    @GetMapping("/{universityId}")
    public ResponseEntity<UniversityDto> getUniversity(@PathVariable String universityId) {
        UniversityDto universityDto = universityService.get(universityId);
        return new ResponseEntity<>(universityDto, HttpStatus.OK);
    }

    //get all
    @GetMapping
    public ResponseEntity<PageableResponse<UniversityDto>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        PageableResponse<UniversityDto> pageableResponse = universityService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }


    //search
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<UniversityDto>> searchUniversity(
            @PathVariable String query,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        PageableResponse<UniversityDto> pageableResponse = universityService.searchByTitle(query, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }

}

