package com.acs560.Clock_debug;

public class Application {

    public static void main(String[] args) {
        // Initialize a Clock instance
        Clock clock = new Clock(13, 45, 30);

        // Display initial time in both formats
        System.out.println("Initial time (24-hour): " + clock.get24HourFormat());
        System.out.println("Initial time (12-hour): " + clock.get12HourFormat());

        // Add a few seconds, minutes, and hours to test
        clock.addSecond();
        System.out.println("After adding one second (24-hour): " + clock.get24HourFormat());
        System.out.println("After adding one second (12-hour): " + clock.get12HourFormat());

        clock.addMinute();
        System.out.println("After adding one minute (24-hour): " + clock.get24HourFormat());
        System.out.println("After adding one minute (12-hour): " + clock.get12HourFormat());

        clock.addHour();
        System.out.println("After adding one hour (24-hour): " + clock.get24HourFormat());
        System.out.println("After adding one hour (12-hour): " + clock.get12HourFormat());
    }
}
