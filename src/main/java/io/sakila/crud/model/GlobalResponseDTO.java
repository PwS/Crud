package io.sakila.crud.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.sakila.crud.util.DateUtil;
import io.sakila.crud.util.ValueUtil;
import org.apache.commons.lang3.time.FastDateFormat;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@JsonInclude(Include.NON_NULL)
public class GlobalResponseDTO<T> {

    @JsonProperty("Status")
    private String status;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("DateTime")
    private String dateTime = FastDateFormat.getInstance("dd-MM-yyyy HH:mm:ss").format(DateUtil.getCurrentTimestamp());

    @JsonProperty("Data")
    private T data;

    public GlobalResponseDTO() {
    }

    public GlobalResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public GlobalResponseDTO(String status, String message, T data) {
        super();
        if (ValueUtil.hasValue(message)) {
            this.message = message;
            this.status = status;
        }
        this.data = data;
    }
}

