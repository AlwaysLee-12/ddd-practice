package hello.ddd.domain.event.async;

import hello.ddd.domain.event.Event;
import hello.ddd.domain.event.OrderCanceledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

//3,4번 방식 공통 사용
public class EventHandler {

    private EventStore eventStore;

    public EventHandler(EventStore eventStore) {
        this.eventStore = eventStore;
    }

//    이벤트 소스 트랜잭션이 커밋될 때에만 이벤트가 발행됨(트랜잭션은 실패했는데 이벤트 핸들러가 실행되는 상황이 발생하지 않음)
//    @TransactionalEventListener(
//            classes = OrderCanceledEvent.class,
//            phase = TransactionPhase.AFTER_COMMIT
//    )
    @EventListener(Event.class)
    public void handle(Event event) {
        eventStore.save(event);
    }
}
