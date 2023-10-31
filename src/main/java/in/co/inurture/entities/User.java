package in.co.inurture.entities;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(name = "user_name")
    private String name;

    @Column(unique = true)
    private String employeeId;

    @Column(name = "user_email", unique = true)
    private String email;

    private String gender;

    @Column(length = 12)
    private String phoneNumber;

    @Column(length = 1000)
    private String about;

    private String domain;

    @Column(length = 500)
    private String address;

    @Column(name = "user_image_name")
    private String imageName;

    @Column(name = "user_password", length = 10)
    private String password;

}
