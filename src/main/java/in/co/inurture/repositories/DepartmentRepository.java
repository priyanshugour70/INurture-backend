package in.co.inurture.repositories;


import in.co.inurture.entities.Department;
import in.co.inurture.entities.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DepartmentRepository extends JpaRepository<Department, String>{

    //search
    Page<Department> findByTitleContaining(String subTitle, Pageable pageable);

    Page<Department> findByUniversity(University university, Pageable pageable);
    //other methods
    //custom finder methods
    //query methods

}
