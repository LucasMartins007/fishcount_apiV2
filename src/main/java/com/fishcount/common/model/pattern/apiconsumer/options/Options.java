package com.fishcount.common.model.pattern.apiconsumer.options;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Lucas Martins
 */
public class Options {

    private static final Map<Option, Object> OPTIONS = new HashMap();

    public static final String ACCEPT_ENCODING = "gzip";

    public static final Integer SOCKET_TIMEOUT = 2000;

    public static void setOption(Option option, Object value) {
        OPTIONS.put(option, value);
    }

    public static Object getOption(Option option) {
        return OPTIONS.get(option);
    }

    static {
        refresh();
    }

    public static void refresh() {
        setOption(Option.OBJECT_MAPPER, new JacksonObjectMapper());
    }

}
