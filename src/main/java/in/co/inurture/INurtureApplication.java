package in.co.inurture;

import in.co.inurture.entities.Role;
import in.co.inurture.entities.User;
import in.co.inurture.repositories.RoleRepository;
import in.co.inurture.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class INurtureApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(INurtureApplication.class, args);
	}

	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository repository;
	@Value("${normal.role.id}")
	private String role_normal_id;
	@Value("${admin.role.id}")
	private String role_admin_id;

	@Autowired
	private UserRepository userRepository;



	@Override
	public void run(String... args) throws Exception {

		try {
			Role role_admin = Role.builder().roleId(role_admin_id).roleName("ROLE_ADMIN").build();
			Role role_normal = Role.builder().roleId(role_normal_id).roleName("ROLE_NORMAL").build();


			User adminUser = User.builder()
					.name("Priyanshu Gour")
					.email("priyanshugour1@gmail.com")
					.employeeId("22BCA3CCM10029")
					.gender("Male")
					.phoneNumber("9098393937")
					.about("I am admin User")
					.domain("Full Stack Java Developer")
					.address("Boys Hostel, Sage University, Bhopal, Madhya Pradesh, India")
					.imageName("default.png")
					.password(passwordEncoder.encode("12345678"))
					.roles(Set.of(role_admin, role_normal))
					.userId(UUID.randomUUID().toString())
					.build();

			User normalUser = User.builder()
					.name("Devanshu Gour")
					.email("devanshugour1@gmail.com")
					.employeeId("22BCA3CCM10001")
					.gender("Male")
					.phoneNumber("9926357417")
					.about("I am Noraml User")
					.domain("Maths,Chemistry,Physics")
					.address("Kota, Rajasthan, India")
					.imageName("default.png")
					.password(passwordEncoder.encode("12345678"))
					.roles(Set.of(role_normal))
					.userId(UUID.randomUUID().toString())
					.build();

			repository.save(role_admin);
			repository.save(role_normal);


			userRepository.save(adminUser);
			userRepository.save(normalUser);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
