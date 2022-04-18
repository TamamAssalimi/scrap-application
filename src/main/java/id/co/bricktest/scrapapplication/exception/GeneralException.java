package id.co.bricktest.scrapapplication.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final String errorCode;
    private final String errorTitle;
    private final String errorMsg;
    private final String sourceSystem;

}
