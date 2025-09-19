package ru.andrewexe.timemanager.controllers;

import org.springframework.web.bind.annotation.*;
import ru.andrewexe.timemanager.dto.EventDto;
import ru.andrewexe.timemanager.dto.EventResponseDto;
import ru.andrewexe.timemanager.services.EventService;

import java.util.List;

@RestController
@RequestMapping("api-v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("create")
    public EventResponseDto createEvent(@RequestBody EventDto eventDto){
        return eventService.createEvent(eventDto);
    }

    @GetMapping("get/{id}")
    public EventResponseDto getEvent(@PathVariable long id){
        return eventService.getEventById(id);
    }

    @GetMapping("get")
    public List<EventResponseDto> getAllEvents(){
        return eventService.getAllEvents();
    }

    @PostMapping("update")
    public EventResponseDto updateEvent(@RequestBody EventResponseDto modifiedDto){
        return eventService.updateEvent(modifiedDto);
    }

    @PostMapping("delete")
    public String deleteEvent(@RequestBody EventDto event){
        return eventService.removeEvent(event);
    }
}