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
@Table(name = "rule_type")
public class RuleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;
    @Column(name = "type")
    private String type;
}
