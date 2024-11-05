package com.acs560.Clock_debug;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    public void testConstructorValidInputs() {
        Clock clock = new Clock(12, 30, 45);
        assertEquals("12:30:45", clock.get24HourFormat());
        
        // Test boundary values
        clock = new Clock(0, 0, 0);
        assertEquals("00:00:00", clock.get24HourFormat());
        
        clock = new Clock(23, 59, 59);
        assertEquals("23:59:59", clock.get24HourFormat());
    }

   

    @Test
    public void testAddSecond() {
        Clock clock = new Clock(12, 30, 59);
        clock.addSecond();
        assertEquals("12:31:00", clock.get24HourFormat());

        // Edge case for 23:59:59 transitioning to 00:00:00
        clock = new Clock(23, 59, 59);
        clock.addSecond();
        assertEquals("00:00:00", clock.get24HourFormat());

        // Check adding seconds over a minute boundary
        clock = new Clock(12, 59, 58);
        clock.addSecond(); // 12:59:59
        clock.addSecond(); // should go to 13:00:00
        assertEquals("13:00:00", clock.get24HourFormat());
        
        // Test multiple second additions
        clock = new Clock(23, 59, 57);
        clock.addSecond(); // 23:59:58
        clock.addSecond(); // 23:59:59
        clock.addSecond(); // 00:00:00
        assertEquals("00:00:00", clock.get24HourFormat());

        // New test: Add second without minute/hour rollover
        clock = new Clock(12, 30, 45);
        clock.addSecond();
        assertEquals("12:30:46", clock.get24HourFormat());
    }

    @Test
    public void testAddMinute() {
        Clock clock = new Clock(12, 59, 30);
        clock.addMinute();
        assertEquals("13:00:30", clock.get24HourFormat());

        // Edge case for 23:59 transitioning to 00:00
        clock = new Clock(23, 59, 0);
        clock.addMinute();
        assertEquals("00:00:00", clock.get24HourFormat());

        // Check adding minutes over an hour boundary
        clock = new Clock(12, 58, 0);
        clock.addMinute(); // should become 12:59:00
        clock.addMinute(); // should go to 13:00:00
        assertEquals("13:00:00", clock.get24HourFormat());
        
        // Test multiple minute additions across day boundary
        clock = new Clock(23, 57, 0);
        clock.addMinute(); // 23:58:00
        clock.addMinute(); // 23:59:00
        clock.addMinute(); // 00:00:00
        assertEquals("00:00:00", clock.get24HourFormat());

        // New test: Add minute without hour rollover
        clock = new Clock(12, 30, 45);
        clock.addMinute();
        assertEquals("12:31:45", clock.get24HourFormat());
    }

    @Test
    public void testAddHour() {
        Clock clock = new Clock(23, 45, 15);
        clock.addHour();
        assertEquals("00:45:15", clock.get24HourFormat());

        // Edge case for 23:00 transitioning to 00:00
        clock = new Clock(23, 0, 0);
        clock.addHour();
        assertEquals("00:00:00", clock.get24HourFormat());

        // Check adding hours over a day boundary
        clock = new Clock(22, 0, 0);
        clock.addHour(); // 23:00:00
        clock.addHour(); // should go to 00:00:00
        assertEquals("00:00:00", clock.get24HourFormat());

        // New test: Add hour without day rollover
        clock = new Clock(12, 30, 45);
        clock.addHour();
        assertEquals("13:30:45", clock.get24HourFormat());
    }

    @Test
    public void testGet12HourFormatAM() {
        Clock clock = new Clock(8, 15, 45);
        assertEquals("8:15:45 AM", clock.get12HourFormat());
        
        // Test early morning hours
        clock = new Clock(1, 0, 0);
        assertEquals("1:00:00 AM", clock.get12HourFormat());
        
        // Test single digit minutes and seconds
        clock = new Clock(2, 5, 7);
        assertEquals("2:05:07 AM", clock.get12HourFormat());
        
        // Test midnight
        clock = new Clock(0, 0, 0);
        assertEquals("12:00:00 AM", clock.get12HourFormat());
    }

    @Test
    public void testGet12HourFormatPM() {
        Clock clock = new Clock(14, 30, 0);
        assertEquals("2:30:00 PM", clock.get12HourFormat());
        
        // Test late evening hours
        clock = new Clock(23, 0, 0);
        assertEquals("11:00:00 PM", clock.get12HourFormat());
        
        // Test transition hour (13:00)
        clock = new Clock(13, 0, 0);
        assertEquals("1:00:00 PM", clock.get12HourFormat());
        
        // Test noon
        clock = new Clock(12, 0, 0);
        assertEquals("12:00:00 PM", clock.get12HourFormat());
    }

    @Test
    public void testMidnight12HourFormat() {
        Clock clock = new Clock(0, 0, 0);
        assertEquals("12:00:00 AM", clock.get12HourFormat());
        
        // Test near midnight
        clock = new Clock(0, 0, 1);
        assertEquals("12:00:01 AM", clock.get12HourFormat());
    }

    @Test
    public void testNoon12HourFormat() {
        Clock clock = new Clock(12, 0, 0);
        assertEquals("12:00:00 PM", clock.get12HourFormat());
        
        // Test near noon
        clock = new Clock(12, 0, 1);
        assertEquals("12:00:01 PM", clock.get12HourFormat());
    }

    @Test
    public void testAMPMBoundary() {
        // Transition from 11:59:59 AM to 12:00:00 PM
        Clock clock = new Clock(11, 59, 59);
        clock.addSecond();
        assertEquals("12:00:00 PM", clock.get12HourFormat());

        // Transition from 11:59:59 PM to 12:00:00 AM
        clock = new Clock(23, 59, 59);
        clock.addSecond();
        assertEquals("12:00:00 AM", clock.get12HourFormat());
    }

    @Test
    public void testPadMethodCoverage() {
        Clock clock = new Clock(9, 5, 7);
        assertEquals("09:05:07", clock.get24HourFormat());

        clock = new Clock(10, 10, 10);
        assertEquals("10:10:10", clock.get24HourFormat());

        // Test padding for single-digit hours
        clock = new Clock(0, 5, 0);
        assertEquals("00:05:00", clock.get24HourFormat());
    }

    // New test: Test toString method
    @Test
    public void testToString() {
        Clock clock = new Clock(14, 30, 45);
        assertEquals("14:30:45", clock.toString());
    }

    // New test: Test equals method
    @Test
    public void testEquals() {
        Clock clock1 = new Clock(14, 30, 45);
        Clock clock2 = new Clock(14, 30, 45);
        Clock clock3 = new Clock(14, 30, 46);
        
        assertTrue(clock1.equals(clock2));
        assertFalse(clock1.equals(clock3));
        assertFalse(clock1.equals(null));
        assertFalse(clock1.equals("14:30:45"));
    }

    // New test: Test hashCode method
    @Test
    public void testHashCode() {
        Clock clock1 = new Clock(14, 30, 45);
        Clock clock2 = new Clock(14, 30, 45);
        Clock clock3 = new Clock(14, 30, 46);
        
        assertEquals(clock1.hashCode(), clock2.hashCode());
        assertNotEquals(clock1.hashCode(), clock3.hashCode());
    }

    @Test
    public void testCombinedOperations() {
        Clock clock = new Clock(23, 58, 58);
        clock.addSecond(); // 23:58:59
        clock.addMinute(); // 23:59:59
        clock.addHour();   // 00:59:59
        assertEquals("00:59:59", clock.get24HourFormat());
        assertEquals("12:59:59 AM", clock.get12HourFormat());
    }

    @Test
    public void testTimeWraparound() {
        // Test hour wraparound with minutes and seconds
        Clock clock = new Clock(23, 59, 59);
        clock.addSecond();
        assertEquals("00:00:00", clock.get24HourFormat());
        assertEquals("12:00:00 AM", clock.get12HourFormat());

        // Test minute wraparound with seconds
        clock = new Clock(23, 59, 0);
        clock.addMinute();
        assertEquals("00:00:00", clock.get24HourFormat());
        assertEquals("12:00:00 AM", clock.get12HourFormat());
    }

    @Test
    public void testCompleteHourCycle() {
        Clock clock = new Clock(0, 0, 0);
        // Test every hour transition
        for (int i = 0; i < 24; i++) {
            String expected24 = String.format("%02d:00:00", i);
            assertEquals(expected24, clock.get24HourFormat());
            if (i < 23) {
                clock.addHour();
            }
        }
    }
    @Test
    public void testEdgeCaseHourAddition() {
        Clock clock = new Clock(23, 59, 59);
        clock.addHour();
        assertEquals("00:59:59", clock.get24HourFormat());
    }

    @Test
    public void testEdgeCaseMinuteAddition() {
        Clock clock = new Clock(23, 59, 59);
        clock.addMinute();
        assertEquals("00:00:59", clock.get24HourFormat());
    }

    @Test
    public void testEdgeCaseSecondAddition() {
        Clock clock = new Clock(23, 59, 59);
        clock.addSecond();
        assertEquals("00:00:00", clock.get24HourFormat());
    }
    
    @Test
    public void testRepeatedOperations() {
        Clock clock = new Clock(12, 0, 0);
        clock.addHour();
        clock.addMinute();
        clock.addSecond();
        assertEquals("13:01:01", clock.get24HourFormat());
    }
    @Test
    public void test12HourFormatEdgeCases() {
        Clock clock = new Clock(12, 0, 0);
        assertEquals("12:00:00 PM", clock.get12HourFormat());

        clock = new Clock(11, 59, 59);
        clock.addSecond();
        assertEquals("12:00:00 PM", clock.get12HourFormat());

        clock = new Clock(0, 0, 0);
        assertEquals("12:00:00 AM", clock.get12HourFormat());

        clock = new Clock(23, 59, 59);
        clock.addSecond();
        assertEquals("12:00:00 AM", clock.get12HourFormat());
    }
    @Test
    public void testZeroTimeInput() {
        Clock clock = new Clock(0, 0, 0);
        assertEquals("00:00:00", clock.get24HourFormat());
    }
  
   
}