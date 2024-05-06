package com.example.backend.repository;

import com.example.backend.entities.IdeologyMorality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeologyMoralityRepository extends JpaRepository<IdeologyMorality, Integer> {

}
