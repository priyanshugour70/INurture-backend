package in.co.inurture.services;

import in.co.inurture.dtos.DepartmentDto;
import in.co.inurture.dtos.PageableResponse;


public interface DepartmentService {


    //create
    DepartmentDto create(DepartmentDto departmentDto);

    //update
    DepartmentDto update(DepartmentDto departmentDto, String departmentId);

    //delete
    void delete(String departmentId);

    //get single

    DepartmentDto get(String departmentId);

    //get all
    PageableResponse<DepartmentDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //search Department
    PageableResponse<DepartmentDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir);


    //create Department with University
    DepartmentDto createWithUniversity(DepartmentDto departmentDto,String universityId);


    //update University of Department
    DepartmentDto updateDepartment(String departmentId,String universityId);

    PageableResponse<DepartmentDto> getAllOfDepartment(String universityId,int pageNumber,int pageSize,String sortBy, String sortDir);

    //other methods


}
