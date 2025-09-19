package ru.andrewexe.timemanager.dto;


import lombok.Value;
import ru.andrewexe.timemanager.models.EventType;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Value
public class EventDto {
    String name;
    List<EventType> type = new ArrayList<>();
    ZonedDateTime startDateTime;
    ZonedDateTime endDateTime;
    EventDto prevEvent;
}
