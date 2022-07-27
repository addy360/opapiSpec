package com.addy360.openApiSpec.controllers;

import com.addy360.openApiSpec.dtos.ApiResponse;
import com.addy360.openApiSpec.dtos.IdeaDto;
import com.addy360.openApiSpec.models.Idea;
import com.addy360.openApiSpec.service.IdeaService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ideas")
@OpenAPIDefinition(
        info = @Info(
                title = "Field of ideas",
                description = "Let the world know what is in that head of yours",
                contact = @Contact(
                        name = "Addy360",
                        email = "info@addy360.co.tz"
                )
        )
)
public class IdeaController {
    private final IdeaService ideaService;

    @GetMapping
    public ResponseEntity<ApiResponse> index() {
        List<Idea> all = ideaService.findAll();
        ApiResponse response = ApiResponse.builder().message("All ideas").data(all).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> show(@PathVariable long id) {
        Optional<Idea> byId = ideaService.findById(id);
        if (!byId.isPresent()) {
            ApiResponse response = ApiResponse.builder().message("Idea with provided id is not found.").build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        ApiResponse response = ApiResponse.builder().message("Idea details").data(byId.get()).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> store(@RequestBody @Valid IdeaDto ideaDto, BindingResult result) {
        if (result.hasErrors()) {
            List<Map<String, Object>> errors = collectErrors(result);

            ApiResponse response = ApiResponse.builder().message("Validation errors").data(errors).build();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }

        Idea idea = ideaService.create(ideaDto);
        ApiResponse response = ApiResponse.builder().message("Idea created successfully.").data(idea).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable long id, @RequestBody @Valid IdeaDto ideaDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<Map<String, Object>> errors = collectErrors(bindingResult);

            ApiResponse response = ApiResponse.builder().message("Validation errors").data(errors).build();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
        }

        Idea idea = ideaService.update(ideaDto, id);
        ApiResponse response = ApiResponse.builder().message("Idea updated successfully.").data(idea).build();
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable long id) {
        ideaService.delete(id);

        ApiResponse response = ApiResponse.builder().message("Idea deleted successfully.").build();
        return ResponseEntity.ok(response);
    }

    private List<Map<String, Object>> collectErrors(BindingResult result) {
        return result.getFieldErrors().stream().map(objectError -> {
            Map<String, Object> error = new HashMap<>();
            error.put(objectError.getField(), objectError.getDefaultMessage());
            return error;
        }).collect(Collectors.toList());
    }
}
