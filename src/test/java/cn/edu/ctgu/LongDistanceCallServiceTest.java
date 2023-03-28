package cn.edu.ctgu;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
class LongDistanceCallServiceTest {
    private LongDistanceCallService service = new LongDistanceCallService();

    @Test
    public void testZeroDurationCall() {
        double cost = service.calculateCallCost(0, LocalDateTime.of(2023, 3, 26, 10, 0));
        assertEquals(0.0, cost, 0.001);
    }

    @Test
    public void testLessThanOneMinuteCall() {
        double cost = service.calculateCallCost(30, LocalDateTime.of(2023, 3, 26, 10, 0));
        assertEquals(0.05, cost, 0.001);
    }

    @Test
    public void testShortDurationCall() {
        double cost = service.calculateCallCost(10 * 60, LocalDateTime.of(2023, 3, 26, 10, 0));
        assertEquals(0.5, cost, 0.001);
    }

    @Test
    public void testLongDurationCall() {
        double cost = service.calculateCallCost(30 * 60, LocalDateTime.of(2023, 3, 26, 10, 0));
        assertEquals(2.0, cost, 0.001);
    }

    @Test
    public void testDaylightSavingTimeStart() {
        double cost = service.calculateCallCost(10 * 60, LocalDateTime.of(2023, 3, 12, 1, 0));
        assertEquals(0.5, cost, 0.001);
    }

    @Test
    public void testStandardTime() {
        double cost = service.calculateCallCost(10 * 60, LocalDateTime.of(2023, 11, 1, 10, 0));
        assertEquals(0.5, cost, 0.001);
    }


}