package ru.andrewexe.timemanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.andrewexe.timemanager.models.Event;

import java.time.ZonedDateTime;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByNameAndEndDateTime(String name, ZonedDateTime endDateTime);
}
