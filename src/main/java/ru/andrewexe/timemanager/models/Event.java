package ru.andrewexe.timemanager.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Long id;

    @Column(name = "task_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "Id", cascade = CascadeType.ALL)
    private List<EventType> types = new ArrayList<>();

    private ZonedDateTime startDateTime;

    @Column(nullable = false)
    private ZonedDateTime endDateTime;

    // TODO: add json

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_event_id")
    private Event parentEvent;

    @OneToMany(mappedBy = "parentEvent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> childEvents = new ArrayList<>();

    public Event(String name, ZonedDateTime startDateTime, ZonedDateTime endDateTime, Event previousEvent) {
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.parentEvent = previousEvent;
    }
}
