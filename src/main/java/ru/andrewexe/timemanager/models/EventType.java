package ru.andrewexe.timemanager.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class EventType
{
    @Id
    @GeneratedValue
    @Column(name = "type_id")
    private long Id;

    @Column(name = "type_name")
    private String name;
}
