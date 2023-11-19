package in.co.inurture.services.impl;

import in.co.inurture.dtos.ClassroomDto;
import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.entities.Classroom;
import in.co.inurture.entities.Department;
import in.co.inurture.exceptions.ResourceNotFoundException;
import in.co.inurture.helper.Helper;
import in.co.inurture.repositories.ClassroomRepository;
import in.co.inurture.repositories.DepartmentRepository;
import in.co.inurture.services.ClassroomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ClassroomServiceImpl implements ClassroomService {


    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    //other dependency
    @Override
    public ClassroomDto create(ClassroomDto classroomDto) {


        Classroom classroom = mapper.map(classroomDto, Classroom.class);

        //product id
        String classroomId = UUID.randomUUID().toString();
        classroom.setClassroomId(classroomId);

        Classroom saveclassroom = classroomRepository.save(classroom);
        return mapper.map(saveclassroom, ClassroomDto.class);
    }

    @Override
    public ClassroomDto update(ClassroomDto classroomDto, String classroomId) {

        //fetch the department of given id
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("Classroom not found of given Id !!"));
        classroom.setTitle(classroomDto.getTitle());
        classroom.setDescription(classroomDto.getDescription());

//        save the entity
        Classroom updatedClassroom = classroomRepository.save(classroom);
        return mapper.map(updatedClassroom, ClassroomDto.class);
    }

    @Override
    public void delete(String classroomId) {
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("Classroom not found of given Id !!"));
        classroomRepository.delete(classroom);
    }

    @Override
    public ClassroomDto get(String classroomId) {
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("Classroom not found of given Id !!"));
        return mapper.map(classroom, ClassroomDto.class);
    }

    @Override
    public PageableResponse<ClassroomDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Classroom> page = classroomRepository.findAll(pageable);
        return Helper.getPageableResponse(page, ClassroomDto.class);
    }


    @Override
    public PageableResponse<ClassroomDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Classroom> page = classroomRepository.findByTitleContaining(subTitle, pageable);
        return Helper.getPageableResponse(page, ClassroomDto.class);
    }

    @Override
    public ClassroomDto createWithDepartment(ClassroomDto classroomDto, String departmentId) {
        //fetch the Department from db:
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department not found !!"));
        Classroom classroom = mapper.map(classroomDto, Classroom.class);

        //Classroom id
        String classroomId = UUID.randomUUID().toString();
        classroom.setClassroomId(classroomId);
        //added
        classroom.setDepartment(department);
        Classroom saveClassroom = classroomRepository.save(classroom);
        return mapper.map(saveClassroom, ClassroomDto.class);
    }

    @Override
    public ClassroomDto updateClassroom(String classroomId, String departmentId) {
        //Department fetch
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() -> new ResourceNotFoundException("Classroom of given id not found !!"));
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department of given id not found !!"));
        classroom.setDepartment(department);
        Classroom savedClassroom = classroomRepository.save(classroom);
        return mapper.map(savedClassroom, ClassroomDto.class);
    }

    @Override
    public PageableResponse<ClassroomDto> getAllOfClassroom(String departmentId, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new ResourceNotFoundException("Department of given id not found !!"));
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Classroom> page = classroomRepository.findByDepartment(department, pageable);
        return Helper.getPageableResponse(page, ClassroomDto.class);
    }

}
