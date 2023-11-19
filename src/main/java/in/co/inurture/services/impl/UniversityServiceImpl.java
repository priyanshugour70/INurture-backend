package in.co.inurture.services.impl;

import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.dtos.UniversityDto;
import in.co.inurture.entities.University;
import in.co.inurture.entities.Zone;
import in.co.inurture.exceptions.ResourceNotFoundException;
import in.co.inurture.helper.Helper;
import in.co.inurture.repositories.UniversityRepository;
import in.co.inurture.repositories.ZoneRepository;
import in.co.inurture.services.UniversityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ZoneRepository zoneRepository;

    //other dependency
    @Override
    public UniversityDto create(UniversityDto universityDto) {


        University university = mapper.map(universityDto, University.class);

        //product id
        String universityId = UUID.randomUUID().toString();
        university.setUniversityId(universityId);

        University saveUniversity = universityRepository.save(university);
        return mapper.map(saveUniversity, UniversityDto.class);
    }

    @Override
    public UniversityDto update(UniversityDto universityDto, String universityId) {

        //fetch the univeristy of given id
        University university = universityRepository.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University not found of given Id !!"));
        university.setTitle(universityDto.getTitle());
        university.setDescription(universityDto.getDescription());
        university.setAddress(universityDto.getAddress());

//        save the entity
        University updatedUniversity = universityRepository.save(university);
        return mapper.map(updatedUniversity, UniversityDto.class);
    }

    @Override
    public void delete(String universityId) {
        University university = universityRepository.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University not found of given Id !!"));
        universityRepository.delete(university);
    }

    @Override
    public UniversityDto get(String universityId) {
        University university = universityRepository.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("Product not found of given Id !!"));
        return mapper.map(university, UniversityDto.class);
    }

    @Override
    public PageableResponse<UniversityDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<University> page = universityRepository.findAll(pageable);
        return Helper.getPageableResponse(page, UniversityDto.class);
    }


    @Override
    public PageableResponse<UniversityDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<University> page = universityRepository.findByTitleContaining(subTitle, pageable);
        return Helper.getPageableResponse(page, UniversityDto.class);
    }

    @Override
    public UniversityDto createWithZone(UniversityDto universityDto, String zoneId) {
        //fetch the Zone from db:
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("Zone not found !!"));
        University university = mapper.map(universityDto, University.class);

        //University id
        String universityId = UUID.randomUUID().toString();
        university.setUniversityId(universityId);
        //added
        university.setZone(zone);
        University saveUniversity = universityRepository.save(university);
        return mapper.map(saveUniversity, UniversityDto.class);


    }

    @Override
    public UniversityDto updateZone(String universityId, String zoneId) {
        //university fetch
        University university = universityRepository.findById(universityId).orElseThrow(() -> new ResourceNotFoundException("University of given id not found !!"));
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("Zone of given id not found !!"));
        university.setZone(zone);
        University savedUniversity = universityRepository.save(university);
        return mapper.map(savedUniversity, UniversityDto.class);
    }

    @Override
    public PageableResponse<UniversityDto> getAllOfZone(String zoneId, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("Zone of given id not found !!"));
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<University> page = universityRepository.findByZone(zone, pageable);
        return Helper.getPageableResponse(page, UniversityDto.class);
    }
}
