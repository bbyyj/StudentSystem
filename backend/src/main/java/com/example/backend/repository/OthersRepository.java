package com.example.backend.repository;

import com.example.backend.entities.Others;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OthersRepository extends JpaRepository<Others, Integer> {

}
