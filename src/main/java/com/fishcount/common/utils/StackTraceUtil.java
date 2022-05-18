package com.fishcount.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

/**
 *
 * @author lucas
 */
public class StackTraceUtil {

    private static final String CAUSE = "Cause:";
    private static final String CAUSE_BY = "Caused By:";
    private static final String NEXT_EXCEPTION = "Next exception:";

    public static String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        fillStackTrace(ex, pw);
        return sw.toString();
    }

    public static String getStackTrace(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        fillStackTrace(ex, pw);
        return sw.toString();
    }

    private static void fillStackTrace(Throwable t, PrintWriter w) {
        if (t == null) {
            return;
        }
        t.printStackTrace(w);
        if (t instanceof SQLException) {
            Throwable cause = ((SQLException) t).getNextException();
            if (cause != null) {
                w.println(NEXT_EXCEPTION);
                fillStackTrace(cause, w);
            }
        } else {
            Throwable cause = t.getCause();
            if (cause != null) {
                w.println(CAUSE);
                fillStackTrace(cause, w);
            }
        }
    }

    public static String getStackTraceSemFill(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }

    public static String getCauseFromStringStackTrace(String stackTrace) {
        String retorno = null;
        if (stackTrace != null) {
            int posicaoStack = stackTrace.indexOf(NEXT_EXCEPTION);
            int posicaoCause = stackTrace.indexOf(CAUSE);
            int posicaoCauseBy = stackTrace.indexOf(CAUSE_BY);
            if (posicaoStack > 0) {
                retorno = stackTrace.substring(posicaoStack);
            } else if (posicaoCauseBy > 0) {
                retorno = stackTrace.substring(posicaoCauseBy);
            } else if (posicaoCause > 0) {
                retorno = stackTrace.substring(posicaoCause);
            } else {
                retorno = stackTrace;
            }
        }
        return retorno;
    }
}
