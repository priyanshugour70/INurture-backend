package in.co.inurture.entities;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classrooms")
public class Classroom {

    @Id
    private String classroomId;

    @Column(nullable = false)
    private String title;

    @Column(length = 10000)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private  Department department;

}
