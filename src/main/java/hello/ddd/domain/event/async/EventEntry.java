package hello.ddd.domain.event.async;

//3,4번 방식 공통 사용
//이벤트 핸들러는 멱등성을 가져 동일한 이벤트를 중복해서 발생시키더라도 결과적으로 동일한 상태를 보장한다.
//이벤트 적용 시 추가 고려할 점 : 이벤트 발생 주체를 추가할지, 전송 실패 재전송 횟수 제한, 이벤트 손실, 이벤트 순서, 이벤트 재처리(멱등성)
public class EventEntry {

    private long id;
    private String type;
    private String contentType;
    private String payload;
    private long timestamp;

    public EventEntry(String type, String contentType, String payload) {
        this.type = type;
        this.contentType = contentType;
        this.payload = payload;
    }

    public EventEntry(long id, String type, String contentType, String payload, long timestamp) {
        this.id = id;
        this.type = type;
        this.contentType = contentType;
        this.payload = payload;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getContentType() {
        return contentType;
    }

    public String getPayload() {
        return payload;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
