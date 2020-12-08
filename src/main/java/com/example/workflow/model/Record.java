package com.example.workflow.model;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Table(name = "RECORD")
@Entity
@RequiredArgsConstructor
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String record;
}
