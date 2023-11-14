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
@Table(name = "zones")
public class Zone {

    @Id
    @Column(name = "id")
    private String zoneId;

    @Column(name = "zone_title", length = 60, nullable = false)
    private String title;

    @Column(name = "zone_desc", length = 500)
    private String description;

    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<University> universities = new ArrayList<>();

}
