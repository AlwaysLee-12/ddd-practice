package hello.ddd.domain.event.async;

import java.util.List;

//3,4번 방식 공통 사용
public interface EventStore {

    void save(Object event);
    List<EventEntry> get(long offset, long limit);
}
