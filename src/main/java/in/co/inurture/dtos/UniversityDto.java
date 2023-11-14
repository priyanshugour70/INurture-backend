package in.co.inurture.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UniversityDto {
    private String universityId;
    private String title;
    private String description;
    private String address;
    private ZoneDto zone;
}
