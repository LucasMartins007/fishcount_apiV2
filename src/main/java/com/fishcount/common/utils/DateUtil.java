/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fishcount.common.utils;

import com.fishcount.common.model.pattern.enums.EnumDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author lucas
 */
public class DateUtil {
    public static final String TIMEZONE = "America/Sao_Paulo";

    public static final ZoneId ZONE_ID = ZoneId.of(TIMEZONE);

    public static final Locale LOCALE_BRAZIL = new Locale("pt", "BR");

    private static final SimpleDateFormat DDMMYYYY = EnumDateFormat.DDMMYYYY.getFormat();

    private static final SimpleDateFormat HHMM = EnumDateFormat.HHMM.getFormat();

    private static final SimpleDateFormat DDMMYYYYHHMMSS = EnumDateFormat.DDMMYYYYHHMMSS.getFormat();

    public static String format(EnumDateFormat dataFormat, Date date) {
        return date != null ? dataFormat.getFormat().format(date) : "";
    }

    public static String formatHHMM(Date date) {
        return date != null ? HHMM.format(date) : "";
    }

    public static String formatDDMMYYYY(Date date) {
        return date != null ? DDMMYYYY.format(date) : "";
    }

    public static String formatDDMMYYYYHHMMSS(Date date) {
        return date != null ? DDMMYYYYHHMMSS.format(date) : "";
    }

    public static Date parse(Date date, EnumDateFormat dateFormat) {
        try {
            if (dateFormat == null) {
                dateFormat = EnumDateFormat.YYYYMMDDTHHMMSS;
            }

            final String dataFormat = dateFormat.format(date);

            return dateFormat.parse(dataFormat);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static LocalTime toLocalTime(Date date) {
        return date.toInstant().atZone(ZONE_ID).toLocalTime();
    }

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZONE_ID).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZONE_ID).toLocalDateTime();
    }

    public static Integer calculateDifference(ChronoUnit unit, Date dateInicial, Date dateFinal) {
        final LocalDateTime ldInitial = toLocalDateTime(dateInicial);
        final LocalDateTime ldFinal = toLocalDateTime(dateFinal);

        return Math.toIntExact(unit.between(ldInitial, ldFinal));
    }

    public static Date getDate() {
        return getCalendar().getTime();
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance(LOCALE_BRAZIL);
    }

    public static Calendar getCalendar(Date data) {
        final Calendar calendar = Calendar.getInstance(LOCALE_BRAZIL);
        calendar.setTime(data);
        return calendar;
    }

    public static int compareTo(Date dateInit, Date dateFinal, EnumDateFormat dateFormat) {
        if (dateInit == null) {
            dateInit = getDate();
        }

        if (dateFinal == null) {
            dateFinal = getDate();
        }

        dateInit = parse(dateInit, dateFormat);
        dateFinal = parse(dateFinal, dateFormat);

        return dateInit.compareTo(dateFinal);
    }

    public static Integer dayOfWeek(Date date) {
        if (date == null) {
            date = getDate();
        }

        return getCalendar(date).get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static Date add(int calendarField, int amount) {
        return add(null, calendarField, amount);
    }

    /**
     * @param data
     * @param calendarField = Calendar.MONTH
     * @param amount        = int
     * @return
     */
    public static Date add(Date data, int calendarField, int amount) {
        if (data == null) {
            data = getDate();
        }

        final Calendar calendar = getCalendar(data);
        calendar.add(calendarField, amount);

        return calendar.getTime();
    }

    public static Date subtract(int calendarField, int amount) {
        return subtract(null, calendarField, amount);
    }

    public static Date subtract(Date data, int calendarField, int amount) {
        return add(data, calendarField, -amount);
    }

    public static String formatMinutes(Integer minutes) {
        if (minutes < 60) {
            return minutes + " min";
        }

        final Integer hour = minutes / 60;
        final Integer minute = minutes - ((hour * 60 * 60) / 60);

        return hour + " h " + minute + " min";
    }

    public static Calendar getCalendarWithHour(final Date dateParameter, final Date dataWithHour) {
        final Calendar calendarHour = DateUtil.getCalendar(dataWithHour);

        final Calendar calendarDate = DateUtil.getCalendar(dateParameter);
        calendarDate.set(Calendar.HOUR_OF_DAY, calendarHour.get(Calendar.HOUR_OF_DAY));
        calendarDate.set(Calendar.MINUTE, calendarHour.get(Calendar.MINUTE));
        calendarDate.set(Calendar.SECOND, calendarHour.get(Calendar.SECOND));
        return calendarDate;
    }

    public static Calendar getCalendarWithDay(final Date dateParameter, final Date dateWithDay) {
        final Calendar calendarDay = DateUtil.getCalendar(dateWithDay);

        final Calendar calendarDate = DateUtil.getCalendar(dateParameter);
        calendarDate.set(Calendar.DAY_OF_MONTH, calendarDay.get(Calendar.DAY_OF_MONTH));
        calendarDate.set(Calendar.MONTH, calendarDay.get(Calendar.MONTH));
        calendarDate.set(Calendar.YEAR, calendarDay.get(Calendar.YEAR));
        return calendarDate;
    }

    public static boolean isToday(Date dateParameter) {
        return LocalDate.now().isEqual(toLocalDate(dateParameter));
    }


    public static Date converterLongToDate(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000);
        return calendar.getTime();
    }
}
