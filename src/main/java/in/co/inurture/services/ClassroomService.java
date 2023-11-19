package in.co.inurture.services;

import in.co.inurture.dtos.ClassroomDto;
import in.co.inurture.dtos.PageableResponse;


public interface ClassroomService {


    //create
    ClassroomDto create(ClassroomDto classroomDto);

    //update
    ClassroomDto update(ClassroomDto classroomDto, String classroomId);

    //delete
    void delete(String classroomId);

    //get single

    ClassroomDto get(String classroomId);

    //get all
    PageableResponse<ClassroomDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //search Department
    PageableResponse<ClassroomDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir);


    //create Department with University
    ClassroomDto createWithDepartment(ClassroomDto classroomDto,String departmentId);


    //update University of Department
    ClassroomDto updateClassroom(String classroomId,String departmentId);

    PageableResponse<ClassroomDto> getAllOfClassroom(String classroomId,int pageNumber,int pageSize,String sortBy, String sortDir);

    //other methods


}
