package in.co.inurture.repositories;

import in.co.inurture.entities.University;
import in.co.inurture.entities.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, String> {
    //search
    Page<University> findByTitleContaining(String subTitle, Pageable pageable);

    Page<University> findByZone(Zone zone, Pageable pageable);
    //other methods
    //custom finder methods
    //query methods

}
