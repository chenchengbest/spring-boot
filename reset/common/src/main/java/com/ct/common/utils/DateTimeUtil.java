package com.ct.common.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Date time util.
 *
 * @author chen.cheng
 */
public class DateTimeUtil {

    /**
     * Current date time string.
     *
     * @return the string
     * @author chen.cheng
     */
    public static String currentDateTime() {
        return DateTimeUtil.currentDateTime(DateFormatter.yyyy_MM_dd_HHmmss);
    }

    /**
     * Parse date date.
     *
     * @param dateString the date string
     * @return the date
     * @author chen.cheng
     */
    public static Date parseDate(String dateString) {
        return DateTimeUtil.parseDate(dateString, DateFormatter.yyyy_MM_dd_HHmmss);
    }

    /**
     * Parse date date.
     *
     * @param dateString  the date string
     * @param formatRegex the format regex
     * @return the date
     * @author chen.cheng
     */
    public static Date parseDate(String dateString, String formatRegex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatRegex);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        Instant instant = dateTime.atZone(ZoneId.of("GMT+08:00")).toInstant();
        return Date.from(instant);
    }

    /**
     * Parse date string.
     *
     * @param localDateTime the local date time
     * @param formatRegex   the format regex
     * @return the string
     * @author chen.cheng
     */
    public static String parseDate(LocalDateTime localDateTime, String formatRegex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatRegex);
        return localDateTime.format(formatter);
    }

    /**
     * Parse date string.
     *
     * @param localDate   the local date
     * @param formatRegex the format regex
     * @return the string
     * @author chen.cheng
     */
    public static String parseDate(LocalDate localDate, String formatRegex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatRegex);
        return localDate.format(formatter);
    }
    /**
     * Current date time string.
     *
     * @param format the format
     * @return the string
     * @author chen.cheng
     */
    public static String currentDateTime(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    /**
     * Parse local date local date time.
     *
     * @param dateString  the date string
     * @param formatRegex the format regex
     * @return the local date time
     * @author chen.cheng
     */
    public static LocalDateTime parseLocalDate(String dateString, String formatRegex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatRegex);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return dateTime;
    }

    /**
     * Parse date string string.
     *
     * @param dateString  the date string
     * @param formatRegex the format regex
     * @return the string
     * @author chen.cheng
     */
    public static String parseDateString(String dateString, String formatRegex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateFormatter.yyyy_MM_dd_HHmmss);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return parseDate(dateTime, formatRegex);
    }

    /**
     * Parse date string string.
     *
     * @param dateString     the date string
     * @param srcFormatRegex the src format regex
     * @param formatRegex    the format regex
     * @return the string
     * @author chen.cheng
     */
    public static String parseDateString(String dateString, String srcFormatRegex, String formatRegex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(srcFormatRegex);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return parseDate(dateTime, formatRegex);
    }

    /**
     * Parse short date string string(yyyyMMdd).
     *
     * @param dateString     the date string
     * @param srcFormatRegex the src format regex
     * @param formatRegex    the format regex
     * @return the string
     * @author chen.cheng
     */
    public static String parseShortDateString(String dateString, String srcFormatRegex, String formatRegex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(srcFormatRegex);
        LocalDate date = LocalDate.parse(dateString, formatter);
        return parseDate(date, formatRegex);
    }
    /**
     * Days of range list.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * DateTimeUtil.daysOfRange("2020-05-06", "2020-09-02",DateFormatter.yyyy_MM_dd,DateFormatter.yyyyMMdd)
     * @return the list
     * @author chen.cheng
     */
    public static List<String> daysOfRange(String startDate, String endDate) {
        return daysOfRange(startDate, endDate, DateFormatter.yyyy_MM_dd, DateFormatter.yyyy_MM_dd);
    }

    /**
     * Days of range list.
     *
     * @param startDate    the start date
     * @param endDate      the end date
     * @param paramFormat  the param format
     * @param resultFormat the result format
     * @return the list
     * @author chen.cheng
     */
    public static List<String> daysOfRange(String startDate, String endDate, String paramFormat, String resultFormat) {
        DateTimeFormatter resultFormatter = DateTimeFormatter.ofPattern(resultFormat);
        DateTimeFormatter paramFormatter = DateTimeFormatter.ofPattern(paramFormat);
        LocalDate dateTimeOfStart = LocalDate.parse(startDate, paramFormatter);
        LocalDate dateTimeOfEnd = LocalDate.parse(endDate, paramFormatter);
        List<String> days = new ArrayList<>();
        while (!dateTimeOfStart.isAfter(dateTimeOfEnd)) {
            days.add(dateTimeOfStart.format(resultFormatter));
            dateTimeOfStart = dateTimeOfStart.plusDays(1L);
        }
        return days;
    }

    /**
     * The interface Date formatter.
     *
     * @author chen.cheng
     */
    public interface DateFormatter {
        /**
         * The constant yyyy_MM_dd_HHmmss.
         *
         * @author chen.cheng
         */
        String yyyy_MM_dd_HHmmss = "yyyy-MM-dd HH:mm:ss";

        /**
         * The constant yyyyMMddHHmmss.
         *
         * @author chen.cheng
         */
        String yyyyMMddHHmmss = "yyyyMMddHHmmss";

        /**
         * The constant yyyy_MM_dd.
         *
         * @author chen.cheng
         */
        String yyyy_MM_dd = "yyyy-MM-dd";

        /**
         * The constant yyyyMMdd.
         *
         * @author chen.cheng
         */
        String yyyyMMdd = "yyyyMMdd";

        /**
         * To string string.
         *
         * @return the string
         * @author chen.cheng
         */
        @Override
        String toString();
    }
}
