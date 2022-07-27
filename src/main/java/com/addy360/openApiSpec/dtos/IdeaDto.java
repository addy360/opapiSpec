package com.addy360.openApiSpec.dtos;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class IdeaDto {

    @NotBlank(message = "Please provide meaningful title for your idea.")
    @Min(value = 50, message = "Your title should be self explanatory with at least 50 characters.")

    String title;
    @NotBlank(message = "Please provide description for your idea.")
    @Max(value = 1000, message = "Your Idea should be not more than 1000 characters.")
    String description;
}
