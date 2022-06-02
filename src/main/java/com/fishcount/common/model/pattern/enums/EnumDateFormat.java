package com.fishcount.common.model.pattern.enums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lucas Martins 
 */
public enum EnumDateFormat {

    DDMM(newThreadLocalSimpleDateFormat("dd/MM")),
    DDMMYY(newThreadLocalSimpleDateFormat("dd/MM/yy")),
    DDMMYYYY(newThreadLocalSimpleDateFormat("dd/MM/yyyy")),
    DDMMYYHHMM(newThreadLocalSimpleDateFormat("dd/MM/yy HH:mm")),
    DDMMYYYYHHMM(newThreadLocalSimpleDateFormat("dd/MM/yyyy HH:mm")),
    DDMMYYYYHHMMSS(newThreadLocalSimpleDateFormat("dd/MM/yyyy HH:mm:ss")),
    YYYYMMDD(newThreadLocalSimpleDateFormat("yyyy-MM-dd")),
    YYYYMMDDHHMMSS(newThreadLocalSimpleDateFormat("yyyy-MM-dd HH:mm:ss")),
    YYYYMMDDTHHMMSS(newThreadLocalSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")),
    MMYY(newThreadLocalSimpleDateFormat("MM/yy")),
    MMYYYY(newThreadLocalSimpleDateFormat("MM/yyyy")),
    DDMMMM(newThreadLocalSimpleDateFormat("dd 'de' MMMMM")),
    EXTENSO(newThreadLocalSimpleDateFormat("dd 'de' MMMMM 'de' yyyy")),
    FULLEXTENSO(newThreadLocalSimpleDateFormat("EEEE',' dd 'de' MMMMM 'de' yyyy 'as' HH:mm:ss")),
    HHMM(newThreadLocalSimpleDateFormat("HH:mm"));

    private final ThreadLocal<SimpleDateFormat> formatter;

    EnumDateFormat(final ThreadLocal<SimpleDateFormat> formatter) {
        this.formatter = formatter;
    }

    private static ThreadLocal<SimpleDateFormat> newThreadLocalSimpleDateFormat(final String frmtString) {
        return ThreadLocal.withInitial(() -> new SimpleDateFormat(frmtString));
    }

    public String format(final Date date) {
        return this.formatter.get().format(date);
    }

    public SimpleDateFormat getFormat() {
        return this.formatter.get();
    }

    public Date parse(final String source) throws ParseException {
        return this.formatter.get().parse(source);
    }

    public String toPattern() {
        return this.formatter.get().toPattern();
    }

}
