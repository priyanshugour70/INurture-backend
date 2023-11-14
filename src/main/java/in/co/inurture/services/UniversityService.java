package in.co.inurture.services;

import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.dtos.UniversityDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UniversityService {

    //create
    UniversityDto create(UniversityDto universityDto);

    //update
    UniversityDto update(UniversityDto universityDto, String universityId);

    //delete
    void delete(String universityId);

    //get single

    UniversityDto get(String universityId);

    //get all
    PageableResponse<UniversityDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //search University
    PageableResponse<UniversityDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir);


    //create University with Zones
    UniversityDto createWithZone(UniversityDto productDto,String categoryId);


    //update Zone of University
    UniversityDto updateZone(String productId,String categoryId);

    PageableResponse<UniversityDto> getAllOfZone(String categoryId,int pageNumber,int pageSize,String sortBy, String sortDir);

    //other methods


}

