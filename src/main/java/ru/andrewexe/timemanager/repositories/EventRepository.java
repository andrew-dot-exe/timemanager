package ru.andrewexe.timemanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.andrewexe.timemanager.models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
