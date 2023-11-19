package in.co.inurture.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "universities")
public class University {


    @Id
    private String universityId;

    @Column(nullable = false)
    private String title;

    @Column(length = 10000)
    private String description;

    @Column(length = 500)
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "zone_id")
    private  Zone zone;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Department> departments = new ArrayList<>();
}


