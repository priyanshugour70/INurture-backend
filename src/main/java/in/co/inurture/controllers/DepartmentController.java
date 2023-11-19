package in.co.inurture.controllers;


import in.co.inurture.dtos.ApiResponseMessage;
import in.co.inurture.dtos.ClassroomDto;
import in.co.inurture.dtos.DepartmentDto;
import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.services.ClassroomService;
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


    @Autowired
    private ClassroomService classroomService;

    //create
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto createdDepartment = departmentService.create(departmentDto);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable String departmentId, @RequestBody DepartmentDto departmentDto) {
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
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable String departmentId) {
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


    //create Classroom with Department
    @PostMapping("/{departmentId}/classrooms")
    public ResponseEntity<ClassroomDto> createClassroomWithDepartment(
            @PathVariable("departmentId") String departmentId,
            @RequestBody ClassroomDto dto
    ) {
        ClassroomDto classroomWithDepartment = classroomService.createWithDepartment(dto, departmentId);
        return new ResponseEntity<>(classroomWithDepartment, HttpStatus.CREATED);
    }

    //update Department of Classroom
    @PutMapping("/{departmentId}/classrooms/{classroomId}")
    public ResponseEntity<ClassroomDto> updateDepartmentOfClassroom(
            @PathVariable String departmentId,
            @PathVariable String classroomId
    ) {
        ClassroomDto classroomDto = classroomService.updateClassroom(classroomId, departmentId);
        return new ResponseEntity<>(classroomDto, HttpStatus.OK);
    }

    //get Department of Classroom
    @GetMapping("/{departmentId}/classrooms")
    public ResponseEntity<PageableResponse<ClassroomDto>> getClassroomsOfDepartment(
            @PathVariable String departmentId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {

        PageableResponse<ClassroomDto> response = classroomService.getAllOfClassroom(departmentId,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
