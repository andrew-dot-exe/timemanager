package ru.andrewexe.timemanager.services;

import org.springframework.stereotype.Service;
import ru.andrewexe.timemanager.dto.EventResponseDto;
import ru.andrewexe.timemanager.dto.EventTypeDto;
import ru.andrewexe.timemanager.dto.EventTypeResponseDto;
import ru.andrewexe.timemanager.models.Event;
import ru.andrewexe.timemanager.models.EventType;
import ru.andrewexe.timemanager.repositories.EventTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventTypeService {
    private final EventTypeRepository repository;

    public EventTypeService(EventTypeRepository repository) {
        this.repository = repository;
    }

    public EventTypeResponseDto createType(EventTypeDto eventType){
        EventType type = convertToEventTypeFromDto(eventType);
        repository.save(type);
        return convertEventTypeToEventResponseDto(type);
    }

    public EventTypeResponseDto updateType(Long id, String name){
        Optional<EventType> eventType = repository.findById(id);
        if(eventType.isPresent()){
            eventType.get().setName(name);
            repository.save(eventType.get());
        }
        return null;
    }

    public String deleteTypeById(Long id){
        Optional<EventType> eventType = repository.findById(id);
        if(eventType.isPresent()){
            repository.delete(eventType.get());
            return eventType.get().getName();
        }
        return null;
    }

    public EventTypeResponseDto getEventTypeById(Long id){
        Optional<EventType> eventType = repository.findById(id);
        return eventType.map(this::convertEventTypeToEventResponseDto).orElse(null);
    }

    public List<EventTypeResponseDto> getAllEventTypes(){
        List<EventType> types = repository.findAll();
        ArrayList<EventTypeResponseDto> eventTypeResponseDtos = new ArrayList<>();
        for(EventType eventType: types){
            eventTypeResponseDtos.add(convertEventTypeToEventResponseDto(eventType));
        }
        return eventTypeResponseDtos;
    }

    private EventType convertToEventTypeFromDto(EventTypeDto dto)
    {
        return new EventType(null, dto.getName());
    }

    private EventTypeResponseDto convertEventTypeToEventResponseDto(EventType event){
        return new EventTypeResponseDto(
                event.getId(),
                event.getName()
        );
    }
}
