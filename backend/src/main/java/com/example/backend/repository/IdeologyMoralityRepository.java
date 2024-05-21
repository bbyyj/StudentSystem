package com.example.backend.repository;

import com.example.backend.entities.ExchangeActivity;
import com.example.backend.entities.IdeologyMorality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeologyMoralityRepository extends JpaRepository<IdeologyMorality, Integer> {
    @Modifying
    @Query(value = "select im.*" +
            "from ideology_morality im join student s on im.sid = s.sid " +
            "where s.sid = :sid", nativeQuery = true)
    List<IdeologyMorality> getIdeologyMoralityBySid(@Param("sid") String sid);
}
