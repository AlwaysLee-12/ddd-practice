package hello.ddd.domain.event.async;

import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//3번 방식
@Component
public class EventForwarder {

    private static final int DEFAULT_LIMIT_SIZE = 100;

    private EventStore eventStore;
    private OffsetStore offsetStore;
    private EventSender eventSender;
    private int limitSize = DEFAULT_LIMIT_SIZE;

    public EventForwarder(EventStore eventStore, OffsetStore offsetStore, EventSender eventSender) {
        this.eventStore = eventStore;
        this.offsetStore = offsetStore;
        this.eventSender = eventSender;
    }

    @Scheduled(initialDelay = 1000L, fixedDelay = 1000L)
    public void getAndSend() {
        long nextOffset = getNextOffset();
        List<EventEntry> events = eventStore.get(nextOffset, limitSize);
        if (!events.isEmpty()) {
            int processedCount = sendEvent(events);
            if (processedCount > 0) {
                saveNextOffset(nextOffset + processedCount);
            }
        }
    }

    private long getNextOffset() {
        return offsetStore.get();
    }

    private int sendEvent(List<EventEntry> events) {
        int processedCount = 0;
        try {
            for (EventEntry entry : events) {
                eventSender.send(entry);
                processedCount++;
            }
        } catch (Exception exception) {

        }
        return processedCount;
    }

    private void saveNextOffset(long nextOffset) {
        offsetStore.update(nextOffset);
    }
}
