package in.co.inurture.services;

import in.co.inurture.dtos.PageableResponse;
import in.co.inurture.dtos.ZoneDto;

public interface ZoneService
{

    //create
    ZoneDto create(ZoneDto zoneDto);

    //update
    ZoneDto update(ZoneDto zoneDto, String zoneId);

    //delete
    void delete(String zoneId);

    //get all
    PageableResponse<ZoneDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single zone detail
    ZoneDto get(String zoneId);

    //search:
}
