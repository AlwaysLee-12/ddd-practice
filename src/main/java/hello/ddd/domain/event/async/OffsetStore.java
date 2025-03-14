package hello.ddd.domain.event.async;

//3번 방식
public interface OffsetStore {

    long get();
    void update(long nextOffset);
}
