package cn.edu.ctgu;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LongDistanceCallService {

    public double calculateCallCost(int callDurationSeconds, LocalDateTime callStartTime) {
        double cost = 0.0;

        // Calculate call duration in minutes, rounded up
        int callDurationMinutes = (int) Math.ceil(callDurationSeconds / 60.0);

        // Adjust call start time for daylight saving time
        if (isDuringDaylightSavingTime(callStartTime)) {
            callStartTime = callStartTime.plusHours(1);
        }

        // Apply call rates based on call duration
        if (callDurationMinutes <= 0) {
            cost = 0.0;
        } else if (callDurationMinutes <= 20) {
            cost = callDurationMinutes * 0.05;
        } else {
            cost = 1.0 + (callDurationMinutes - 20) * 0.1;
        }

        return cost;
    }

    private boolean isDuringDaylightSavingTime(LocalDateTime dateTime) {
        // Check if date is between March and November (inclusive)
        if (dateTime.getMonthValue() < 3 || dateTime.getMonthValue() > 11) {
            return false;
        }

        // Check if date is in March or November
        if (dateTime.getMonthValue() == 3 || dateTime.getMonthValue() == 11) {
            // Check if date is on or after the second Sunday of the month
            LocalDate date = dateTime.toLocalDate();
            int dayOfWeek = date.getDayOfWeek().getValue();
            int dayOfMonth = date.getDayOfMonth();
            if (dateTime.getMonthValue() == 3) {
                return (dayOfMonth >= 8 && dayOfWeek == 7) || dayOfMonth >= 15;
            } else {
                return (dayOfMonth >= 1 && dayOfWeek == 7) || (dayOfMonth >= 8 && dayOfMonth <= 14);
            }
        }

        // Date is between April and October (inclusive)
        return true;
    }

}
