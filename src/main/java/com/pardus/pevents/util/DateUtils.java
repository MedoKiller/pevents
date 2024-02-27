package com.pardus.pevents.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtils {

    private DateUtils() {}

    public static Timestamp parseTimestamp(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date parsedDate = dateFormat.parse(dateString);
        return new Timestamp(parsedDate.getTime());
    }

    public static ZonedDateTime timestampToZonedDT(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        // Parse the date string into a LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);

        // Convert LocalDateTime to ZonedDateTime using the system's default time zone
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        // For a specific timezone, you can do the following:
        // zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Paris"));
        return zonedDateTime;
    }

}
