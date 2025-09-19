package ru.andrewexe.timemanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.andrewexe.timemanager.models.EventType;

public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
