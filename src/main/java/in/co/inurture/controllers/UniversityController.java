package in.co.inurture.controllers;

import in.co.inurture.dtos.ApiResponseMessage;
import in.co.inurture.dtos.DepartmentDto;
import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.dtos.UniversityDto;
import in.co.inurture.services.DepartmentService;
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

    @Autowired
    private DepartmentService departmentService;

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


    //create Department with University
    @PostMapping("/{universityId}/departments")
    public ResponseEntity<DepartmentDto> createDepartmentWithUniversity(
            @PathVariable("universityId") String universityId,
            @RequestBody DepartmentDto dto
    ) {
        DepartmentDto departmentWithUniversity = departmentService.createWithUniversity(dto, universityId);
        return new ResponseEntity<>(departmentWithUniversity, HttpStatus.CREATED);
    }

    //update University of Department
    @PutMapping("/{universityId}/departments/{departmentId}")
    public ResponseEntity<DepartmentDto> updateUniversityOfDepartment(
            @PathVariable String universityId,
            @PathVariable String departmentId
    ) {
        DepartmentDto departmentDto = departmentService.updateDepartment(departmentId, universityId);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    //get University of Department
    @GetMapping("/{universityId}/departments")
    public ResponseEntity<PageableResponse<DepartmentDto>> getDepartmentsOfUniversity(
            @PathVariable String universityId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {

        PageableResponse<DepartmentDto> response = departmentService.getAllOfDepartment(universityId,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}

