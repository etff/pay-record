package com.toyproject.payrecord.global.utils;

import java.util.Objects;

public class TimeUtil {

    private TimeUtil() {
    }

    public static int ParseToIntHour(String time) {
        if (Objects.isNull(time) || time.isEmpty() || time.length() != 4) {
            throw new IllegalArgumentException();
        }
        return hours(time) + minutes(time);
    }

    private static int hours(String time) {
        int hour = Integer.parseUnsignedInt(time.substring(0, 2)) * 60;

        if (hour < 0) {
            throw new IllegalArgumentException();
        }
        return hour;
    }

    private static int minutes(String time) {
        int min = Integer.parseUnsignedInt(time.substring(2, 4));

        if (min < 0) {
            throw new IllegalArgumentException();
        }
        return min;
    }
}
