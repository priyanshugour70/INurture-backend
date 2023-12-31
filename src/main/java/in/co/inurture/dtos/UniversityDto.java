package in.co.inurture.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UniversityDto {
    private String universityId;

    @NotBlank(message = "title is required !!")
    @Size(min = 4, message = "title must be of minimum 4 characters")
    private String title;

    @NotBlank(message = "Description required !!")
    private String description;

    @NotBlank(message = "Address required !!")
    private String address;
    private ZoneDto zone;
}
