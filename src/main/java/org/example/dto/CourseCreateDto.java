package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCreateDto {
    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
