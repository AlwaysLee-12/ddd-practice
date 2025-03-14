package hello.ddd.domain.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

//(1) 해결을 위해
// 1. 로컬 핸들러를 비동기로 실행
// 2. 메세지 큐 사용
// 3. 이벤트 저장소와 이벤트 포워더 사용
// 4. 이벤트 저장소와 이벤트 제공 API 사용
@Service
public class OrderCanceledEventHandler {

    private RefundService refundService;

    public OrderCanceledEventHandler(RefundService refundService) {
        this.refundService = refundService;
    }

    //@Async 1번 방법
    @EventListener(OrderCanceledEventHandler.class)
    public void handle(OrderCanceledEvent event) {
        refundService.refund(event.getOrderNumber()); //아직 여기서 문제가 발생했을 때에 대한 트랜잭션 처리와 병목 문제 잔존(1)
    }
}
