package com.addy360.openApiSpec.repository;

import com.addy360.openApiSpec.models.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepo extends JpaRepository<Idea, Long> {
}
