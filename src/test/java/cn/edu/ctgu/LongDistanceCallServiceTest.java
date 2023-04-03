package cn.edu.ctgu;

import cn.edu.ctgu.LongDistanceCallService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LongDistanceCallServiceTest {

    LongDistanceCallService service = new LongDistanceCallService();

    @Test
    void testCalculateTimeSpan_DuringDaylightSavingTime() {
        LocalDateTime startTime = LocalDateTime.of(2023, 3, 12, 1, 30, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 3, 12, 2, 0, 0);
        long expected = 30;
        long actual = service.calculateTimeSpan(startTime, endTime);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateTimeSpan_NotDuringDaylightSavingTime() {
        LocalDateTime startTime = LocalDateTime.of(2023, 4, 3, 10, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 4, 3, 10, 30, 0);
        long expected = 30;
        long actual = service.calculateTimeSpan(startTime, endTime);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateFee_LessThan1Minute() {
        long timeLength = 1;
        double expected = 0.05;
        double actual = service.calculateFee(timeLength);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateFee_LessThanOrEqualTo20Minutes() {
        long timeLength = 20;
        double expected = 1.0;
        double actual = service.calculateFee(timeLength);
        assertEquals(expected, actual);
    }

    @Test
    void testCalculateFee_GreaterThan20Minutes() {
        long timeLength = 25;
        double expected = 1.5;
        double actual = service.calculateFee(timeLength);
        assertEquals(expected, actual);
    }

    @Test
    void testIsDuringDaylightSavingTime_DuringDaylightSavingTime() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 12, 12, 1, 30, 0);
        boolean expected = false;
        boolean actual = service.isDuringDaylightSavingTime(dateTime);
        assertEquals(expected, actual);
    }

    @Test
    void testIsDuringDaylightSavingTime_NotDuringDaylightSavingTime() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 4, 3, 10, 0, 0);
        boolean expected = true;
        boolean actual = service.isDuringDaylightSavingTime(dateTime);
        assertEquals(expected, actual);
    }

    @Test
    void testIsDuringDaylightSavingTimeMarch() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 3, 13, 10, 0, 0);
        boolean expected = true;
        boolean actual = service.isDuringDaylightSavingTime(dateTime);
        assertEquals(expected, actual);
    }

    @Test
    void testIsDuringDaylightSavingTimeNovember() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 11, 13, 10, 0, 0);
        boolean expected = true;
        boolean actual = service.isDuringDaylightSavingTime(dateTime);
        assertEquals(expected, actual);
    }
}
