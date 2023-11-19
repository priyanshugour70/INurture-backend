package in.co.inurture.controllers;


import in.co.inurture.dtos.ApiResponseMessage;
import in.co.inurture.dtos.DepartmentDto;
import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    //create
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto createdDepartment = departmentService.create(departmentDto);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> updateUniversity(@PathVariable String departmentId, @RequestBody DepartmentDto departmentDto) {
        DepartmentDto updatedDepartment = departmentService.update(departmentDto, departmentId);
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }


    //delete
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<ApiResponseMessage> delete(@PathVariable String departmentId) {
        departmentService.delete(departmentId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Department is deleted successfully !!").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    //get single

    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> getUniversity(@PathVariable String departmentId) {
        DepartmentDto departmentDto = departmentService.get(departmentId);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    //get all
    @GetMapping
    public ResponseEntity<PageableResponse<DepartmentDto>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        PageableResponse<DepartmentDto> pageableResponse = departmentService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }


    //search
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<DepartmentDto>> searchDepartment(
            @PathVariable String query,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        PageableResponse<DepartmentDto> pageableResponse = departmentService.searchByTitle(query, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }

}
