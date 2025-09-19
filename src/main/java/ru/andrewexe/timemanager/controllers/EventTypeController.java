package ru.andrewexe.timemanager.controllers;

import org.springframework.web.bind.annotation.*;
import ru.andrewexe.timemanager.dto.EventTypeDto;
import ru.andrewexe.timemanager.dto.EventTypeResponseDto;
import ru.andrewexe.timemanager.services.EventTypeService;

@RestController
@RequestMapping("api-v1/types")
public class EventTypeController {
    private EventTypeService service;

    @PostMapping("create")
    public EventTypeResponseDto addEventType(@RequestBody EventTypeDto dto){
        return service.createType(dto);
    }

    @PostMapping("remove")
    public String removeEventType(@RequestAttribute Long id){
        return service.deleteTypeById(id);
    }
}
