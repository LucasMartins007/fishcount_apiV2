package com.fishcount.common.exception;

import com.fishcount.common.utils.ListUtil;
import com.fishcount.common.utils.StringUtil;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lucas
 */
@Data
public class RespostaException implements Serializable {

    private String message;

    private String trace;

    private Integer status;

    private Date dataHora;

    private List<String> details;

    public RespostaException(String message) {
        this.message = replaceInvalidCaracters(message);
        this.status = HttpStatus.BAD_REQUEST.value();
    }

    public RespostaException(Throwable exception) {
        this(exception.getMessage());

        if (exception instanceof FcRuntimeException) {
            setDetails(((FcRuntimeException) exception).getDetails());

            if (ListUtil.isNullOrEmpty(details) && exception.getCause() instanceof FcRuntimeException) {
                setDetails((List<String>) exception.getCause());
            }
        }
    }

    private String replaceInvalidCaracters(String value) {
        if (StringUtil.isNullOrEmpty(value)) {
            return value;
        }

        return value.replaceAll("\r\n|\r|\n", "<br/>")
                .replaceAll("\t", "&nbsp;&nbsp;");
    }

}
