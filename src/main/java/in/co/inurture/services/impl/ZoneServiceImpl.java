package in.co.inurture.services.impl;

import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.dtos.ZoneDto;
import in.co.inurture.entities.Zone;
import in.co.inurture.exceptions.ResourceNotFoundException;
import in.co.inurture.helper.Helper;
import in.co.inurture.repositories.ZoneRepository;
import in.co.inurture.services.ZoneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ZoneDto create(ZoneDto categoryDto) {
        //creating zoneId:randomly

        String zoneId = UUID.randomUUID().toString();
        categoryDto.setZoneId(zoneId);
        Zone zone = mapper.map(categoryDto, Zone.class);
        Zone savedCategory = categoryRepository.save(zone);
        return mapper.map(savedCategory, ZoneDto.class);
    }

    @Override
    public ZoneDto update(ZoneDto zoneDto, String zoneId) {

        //get zone of given id
        Zone zone = categoryRepository.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("Zone not found with given id !!"));
        //update zone details
        zone.setTitle(zoneDto.getTitle());
        zone.setDescription(zoneDto.getDescription());
        Zone updatedCategory = categoryRepository.save(zone);
        return mapper.map(updatedCategory, ZoneDto.class);
    }

    @Override
    public void delete(String zoneId) {
        //get category of given id
        Zone zone = categoryRepository.findById(zoneId).orElseThrow(() -> new ResourceNotFoundException("Zone not found with given id !!"));
        categoryRepository.delete(zone);
    }

    @Override
    public PageableResponse<ZoneDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Zone> page = categoryRepository.findAll(pageable);
        PageableResponse<ZoneDto> pageableResponse = Helper.getPageableResponse(page, ZoneDto.class);
        return pageableResponse;
    }

    @Override
    public ZoneDto get(String categoryId) {
        Zone zone = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Zone not found with given id !!"));
        return mapper.map(zone, ZoneDto.class);
    }
}
