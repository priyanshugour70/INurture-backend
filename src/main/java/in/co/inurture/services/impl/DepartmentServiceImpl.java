package in.co.inurture.services.impl;

import in.co.inurture.dtos.DepartmentDto;
import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.entities.Department;
import in.co.inurture.entities.University;
import in.co.inurture.exceptions.ResourceNotFoundException;
import in.co.inurture.helper.Helper;
import in.co.inurture.repositories.DepartmentRepository;
import in.co.inurture.repositories.UniversityRepository;
import in.co.inurture.services.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UniversityRepository universityRepository;

    //other dependency
    @Override
    public DepartmentDto create(DepartmentDto departmentDto) {


        Department department = mapper.map(departmentDto, Department.class);

        //product id
        String departmentId = UUID.randomUUID().toString();
        department.setDepartmentId(departmentId);

        Department saveDepartment = departmentRepository.save(department);
        return mapper.map(saveDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto update(DepartmentDto departmentDto, String departmentId) {

        //fetch the department of given id
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department not found of given Id !!"));
        department.setTitle(departmentDto.getTitle());
        department.setDescription(departmentDto.getDescription());

//        save the entity
        Department updatedDepartment = departmentRepository.save(department);
        return mapper.map(updatedDepartment, DepartmentDto.class);
    }

    @Override
    public void delete(String departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department not found of given Id !!"));
        departmentRepository.delete(department);
    }

    @Override
    public DepartmentDto get(String departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given Id !!"));
        return mapper.map(department, DepartmentDto.class);
    }

    @Override
    public PageableResponse<DepartmentDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Department> page = departmentRepository.findAll(pageable);
        return Helper.getPageableResponse(page, DepartmentDto.class);
    }


    @Override
    public PageableResponse<DepartmentDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Department> page = departmentRepository.findByTitleContaining(subTitle, pageable);
        return Helper.getPageableResponse(page, DepartmentDto.class);
    }

    @Override
    public DepartmentDto createWithUniversity(DepartmentDto departmentDto, String universityId) {
        //fetch the University from db:
        University university = universityRepository.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University not found !!"));
        Department department = mapper.map(departmentDto, Department.class);

        //Department id
        String departmentId = UUID.randomUUID().toString();
        department.setDepartmentId(departmentId);
        //added
        department.setUniversity(university);
        Department saveDepartment = departmentRepository.save(department);
        return mapper.map(saveDepartment, DepartmentDto.class);


    }

    @Override
    public DepartmentDto updateDepartment(String departmentId, String universityId) {
        //Department fetch
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department of given id not found !!"));
        University university = universityRepository.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University of given id not found !!"));
        department.setUniversity(university);
        Department savedDepartment = departmentRepository.save(department);
        return mapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public PageableResponse<DepartmentDto> getAllOfDepartment(String universityId, int pageNumber, int pageSize, String sortBy, String sortDir) {
        University university = universityRepository.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University of given id not found !!"));
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Department> page = departmentRepository.findByUniversity(university, pageable);
        return Helper.getPageableResponse(page, DepartmentDto.class);
    }

}
