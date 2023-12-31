package in.co.inurture.dtos;


import in.co.inurture.entities.Role;
import in.co.inurture.validate.ImageNameValid;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;

    @Size(min = 3, max = 20, message = "Invalid Name !!")
    @ApiModelProperty(value = "user_name", name = "username", required = true, notes = "user name of new user !!")
    private String name;

    @Size(min = 5, max = 15, message = "Invalid Employee ID !!")
    private String employeeId;

    // @Email(message = "Invalid User Email..!!")
    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-8-9]+\\.)+[a-z]{2,5}$", message = "Invalid User Email...!!")
    @NotBlank(message = "Email is Required..!!")
    private String email;

    @Size(min = 4, max = 6, message = "Invalid Gender ..!!")
    private String gender;

    @Size(min = 10, max = 12, message = "Invalid Phone Number ..!!")
    private String phoneNumber;

    @NotBlank(message = "Write Something about yourself..!!")
    private String about;

    private Set<RoleDto> roles = new HashSet<>();

    @Size(min = 2, max = 20, message = "Invalid Domain ..!!")
    private String domain;

    @Size(min = 6, max = 100, message = "Invalid Address ..!!")
    private String address;

    @ImageNameValid
    private String imageName;

    @NotBlank(message = "Password is required..!!")
    private String password;

}

