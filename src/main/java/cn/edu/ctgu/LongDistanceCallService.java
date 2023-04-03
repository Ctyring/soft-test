package cn.edu.ctgu;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LongDistanceCallService {
    public long calculateTimeSpan(final LocalDateTime startTime, final LocalDateTime endTime) {
        LocalDateTime start = startTime;
        LocalDateTime end = endTime;
        if (isDuringDaylightSavingTime(startTime)) {
            start = startTime.plusHours(1);
        }
        if (isDuringDaylightSavingTime(endTime)) {
            end = endTime.plusHours(1);
        }
        //        通话时间小于等于20分钟时，每分钟收费0.05美元，通话时间不足1分钟按1分钟计算。
//        通话时间大于20分钟时，收费1.00美元，外加超过20分钟的部分每分钟0.10美元；
//        不到1分钟按1分钟计算
        java.time.Duration duration = java.time.Duration.between(start, end);
        long seconds = duration.getSeconds();
        final int minutePerHour = 60;
        long minutes = seconds / minutePerHour + (seconds % minutePerHour == 0 ? 0 : 1);
        return minutes;
    }

    public double calculateFee(final long timeLength) {
        double fee;
        final double feePerMinute = 0.05;
        final double feePerMinuteAfter20Minutes = 0.10;
        final int feePerMinuteAfter20MinutesThreshold = 20;
        if (timeLength <= feePerMinuteAfter20MinutesThreshold) {
            fee = timeLength * feePerMinute;
        } else {
            fee = 1 + (timeLength - feePerMinuteAfter20MinutesThreshold) * feePerMinuteAfter20Minutes;
        }
        return fee;
    }

    public boolean isDuringDaylightSavingTime(final LocalDateTime dateTime) {
        // Check if date is between March and November (inclusive)
        final int march = 3;
        final int november = 11;
        final int secondSunday = 7;
        final int minDayOfMonth = 8;
        final int maxDayOfMonth = 14;
        final int maxDayOfA = 15;

        if (dateTime.getMonthValue() < march || dateTime.getMonthValue() > november) {
            return false;
        }

        // Check if date is in March or November
        if (dateTime.getMonthValue() == march || dateTime.getMonthValue() == november) {
            // Check if date is on or after the second Sunday of the month
            LocalDate date = dateTime.toLocalDate();
            int dayOfWeek = date.getDayOfWeek().getValue();
            int dayOfMonth = date.getDayOfMonth();
            if (dateTime.getMonthValue() == march) {
                return (dayOfMonth >= minDayOfMonth && dayOfWeek == secondSunday) || dayOfMonth >= maxDayOfA;
            } else {
                return dayOfWeek == secondSunday || dayOfMonth >= minDayOfMonth && dayOfMonth <= maxDayOfMonth;
            }
        }

        // Date is between April and October (inclusive)
        return true;
    }

}
