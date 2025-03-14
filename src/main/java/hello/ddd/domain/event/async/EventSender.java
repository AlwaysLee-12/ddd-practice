package hello.ddd.domain.event.async;

public interface EventSender {

    void send(EventEntry event);
}
