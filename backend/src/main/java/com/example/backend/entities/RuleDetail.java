package com.example.backend.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rule_detail")
public class RuleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;
    @Column(name = "tid")
    private int tid;
    @Column(name = "score")
    private float score;
    @Column(name = "detail")
    private String title;
    @Column(name = "lvl")
    private int level;
}
