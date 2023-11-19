package in.co.inurture.dtos;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ClassroomDto {

    private String classroomId;

    @NotBlank(message = "title is required !!")
    @Size(min = 4, message = "title must be of minimum 4 characters")
    private String title;

    @NotBlank(message = "Description required !!")
    private String description;

    private DepartmentDto department;

}
