package com.addy360.openApiSpec.dtos;


import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class IdeaDto {

    @NotBlank(message = "Please provide meaningful title for your idea.")
    @Size(max = 50, min = 10, message = "Your title should be self explanatory with at least 50 characters.")

    String title;
    @NotBlank(message = "Please provide description for your idea.")
    @Size(max = 1000, min = 100, message = "Your Idea should be not more than 1000 characters.")
    String description;
}
