package in.co.inurture.repositories;


import in.co.inurture.entities.Classroom;
import in.co.inurture.entities.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClassroomRepository extends JpaRepository<Classroom, String>{

    //search
    Page<Classroom> findByTitleContaining(String subTitle, Pageable pageable);

    Page<Classroom> findByDepartment(Department department, Pageable pageable);
    //other methods
    //custom finder methods
    //query methods

}
