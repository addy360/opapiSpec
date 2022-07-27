package com.addy360.openApiSpec.service;


import com.addy360.openApiSpec.dtos.IdeaDto;
import com.addy360.openApiSpec.models.Idea;
import com.addy360.openApiSpec.repository.IdeaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IdeaService {
    private final IdeaRepo ideaRepo;

    public List<Idea> findAll() {
        return ideaRepo.findAll();
    }

    public Optional<Idea> findById(long id) {
        return ideaRepo.findById(id);
    }

    public Idea create(IdeaDto ideaDto) {
        Idea idea = new Idea();
        idea.setTitle(ideaDto.getTitle());
        idea.setDescription(ideaDto.getDescription());
        return ideaRepo.save(idea);
    }

    public Idea update(IdeaDto ideaDto, long id) {
        Optional<Idea> byId = findById(id);
        if (!byId.isPresent()) return null;

        Idea idea = byId.get();
        idea.setTitle(ideaDto.getTitle());
        idea.setDescription(ideaDto.getDescription());
        return ideaRepo.save(idea);
    }

    public void delete(long id) {
        Idea idea = findById(id).orElse(null);
        if (idea == null) return;
        ideaRepo.delete(idea);
    }
}
