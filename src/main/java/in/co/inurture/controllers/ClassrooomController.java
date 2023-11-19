package in.co.inurture.controllers;


import in.co.inurture.dtos.ApiResponseMessage;
import in.co.inurture.dtos.ClassroomDto;
import in.co.inurture.dtos.DepartmentDto;
import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classrooms")
public class ClassrooomController {

    @Autowired
    private ClassroomService classroomService;

    //create
    @PostMapping
    public ResponseEntity<ClassroomDto> createClassroom(@RequestBody ClassroomDto classroomDto) {
        ClassroomDto createdClassroom = classroomService.create(classroomDto);
        return new ResponseEntity<>(createdClassroom, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{classroomId}")
    public ResponseEntity<ClassroomDto> updateClassroom(@PathVariable String classroomId, @RequestBody ClassroomDto classroomDto) {
        ClassroomDto updatedClassroom = classroomService.update(classroomDto, classroomId);
        return new ResponseEntity<>(updatedClassroom, HttpStatus.OK);
    }


    //delete
    @DeleteMapping("/{classroomId}")
    public ResponseEntity<ApiResponseMessage> delete(@PathVariable String classroomId) {
        classroomService.delete(classroomId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Classroom is deleted successfully !!").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

    //get single

    @GetMapping("/{classroomId}")
    public ResponseEntity<ClassroomDto> getClassroom(@PathVariable String classroomId) {
        ClassroomDto classroomDto = classroomService.get(classroomId);
        return new ResponseEntity<>(classroomDto, HttpStatus.OK);
    }

    //get all
    @GetMapping
    public ResponseEntity<PageableResponse<ClassroomDto>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        PageableResponse<ClassroomDto> pageableResponse = classroomService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }


    //search
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ClassroomDto>> searchClassrooms(
            @PathVariable String query,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir

    ) {
        PageableResponse<ClassroomDto> pageableResponse = classroomService.searchByTitle(query, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }

}
