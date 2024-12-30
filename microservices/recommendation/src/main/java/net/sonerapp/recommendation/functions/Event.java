package net.sonerapp.recommendation.functions;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event<T, K> {
    public enum Type {
        CREATE,
        UPDATE,
        DELETE
    }

    private Event.Type eventType;

    private T data;

    private K key;

    private LocalDateTime localDateTime;

    public Event() {
        this.eventType = null;
        this.data = null;
        this.key = null;
        this.localDateTime = null;
    }

    public Event(Type eventType, T data, K key) {
        this.eventType = eventType;
        this.data = data;
        this.key = key;
        this.localDateTime = LocalDateTime.now();
    }

}
