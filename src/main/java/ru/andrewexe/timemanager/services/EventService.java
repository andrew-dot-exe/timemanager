package ru.andrewexe.timemanager.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestAttribute;
import ru.andrewexe.timemanager.dto.EventDto;
import ru.andrewexe.timemanager.dto.EventResponseDto;
import ru.andrewexe.timemanager.models.Event;
import ru.andrewexe.timemanager.repositories.EventRepository;
import ru.andrewexe.timemanager.repositories.EventTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Добавляет событие в базу данных
     * @param eventDto - DTO события
     * @return EventResponseDto - ответ в виде DTO с указанием ID созданной записи.
     */
    public EventResponseDto createEvent(EventDto eventDto){
        Event previousEvent = null;
        if(eventDto.getPrevEvent() != null){
            previousEvent = findByDto(eventDto.getPrevEvent());
        }
        Event response = eventRepository.save(new Event(eventDto.getName(),
                eventDto.getStartDateTime(), eventDto.getEndDateTime(), previousEvent));

        return convertToResponseDto(response);
    }

    /**
     * Возврашает список
     * @return список DTO всех событий
     */
    public List<EventResponseDto> getAllEvents(){
        List<Event> events = eventRepository.findAll();
        ArrayList<EventResponseDto> responseDtoList = new ArrayList<>();
        for(Event event: events){
            responseDtoList.add(convertToResponseDto(event));
        }
        return responseDtoList;
    }

    public EventResponseDto updateEvent(EventResponseDto modifiedEventResponse){
        Event originalEvent = eventRepository.getReferenceById(modifiedEventResponse.getId());
        originalEvent.setName(modifiedEventResponse.getName());
        originalEvent.setStartDateTime(modifiedEventResponse.getStartDateTime());
        originalEvent.setEndDateTime(modifiedEventResponse.getEndDateTime());
        originalEvent.setTypes(modifiedEventResponse.getType());
        originalEvent.setParentEvent(
                eventRepository.getReferenceById(modifiedEventResponse.getPrevEvent().getId())
        );
        return null;
    }

    public String removeEvent(EventDto event){
        Event toRemove = findByDto(event);
        if(toRemove != null){
            eventRepository.delete(toRemove);
            return event.getName();
        }
        return null;
    }

    public EventResponseDto getEventById(@RequestAttribute Long id){
        return convertToResponseDto(eventRepository.getReferenceById(id));
    }

    private Event findByDto(EventDto eventDto){
        return eventRepository.findByNameAndEndDateTime(eventDto.getName(), eventDto.getEndDateTime());
    }

    private EventResponseDto convertToResponseDto(Event event){
        if(event.getParentEvent() != null){
            return new EventResponseDto(
                    event.getId(),
                    event.getName(),
                    event.getStartDateTime(),
                    event.getEndDateTime(),
                    convertToResponseDto(event.getParentEvent())
            );
        }
        return new EventResponseDto(
                event.getId(),
                event.getName(),
                event.getStartDateTime(),
                event.getEndDateTime(),
                null
        );
    }
}