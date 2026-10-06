package jobportal.domain.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class ApiErrorResponse {
    private int status;
    private String code;
    private long timeStamp;
    private Map<String, String> messages;

    public ApiErrorResponse(int status, String code, Map<String, String> messages) {
        this.status = status;
        this.code = code.toUpperCase();
        this.timeStamp = System.currentTimeMillis();
        this.messages = messages;
    }
}
