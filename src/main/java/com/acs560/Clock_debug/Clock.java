package com.acs560.Clock_debug;

import java.util.Objects;

public class Clock {
    private int hours;
    private int minutes;
    private int seconds;

    /**
     * Constructor
     * @param hours - the initial hours
     * @param minutes - the initial minutes
     * @param seconds - the initial seconds
     */
    public Clock(int hours, int minutes, int seconds) {
        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Hours must be 0-23, minutes and seconds must be 0-59.");
        }
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /**
     * Add an hour to the clock.
     */
    public void addHour() {
        hours = (hours + 1) % 24;
    }

    /**
     * Add a minute to the clock.
     */
    public void addMinute() {
        minutes++;
        if (minutes == 60) {
            minutes = 0;
            addHour();
        }
    }

    /**
     * Add a second to the clock.
     */
    public void addSecond() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            addMinute();
        }
    }

    /**
     * Get the time in 24 hour format: hh:mm:ss
     * @return - the 24 hour format time
     */
    public String get24HourFormat() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Get the 12 hour format: h:mm:ss AM/PM
     * @return - the 12 hour format
     */
    public String get12HourFormat() {
        int displayHour = (hours % 12 == 0) ? 12 : hours % 12;
        String suffix = (hours < 12) ? "AM" : "PM";
        return String.format("%d:%02d:%02d %s", displayHour, minutes, seconds, suffix);
    }

    /**
     * Pads a value as a two-digit representation.
     * e.g. 9 is "09"
     * @param value - the value to pad
     * @return - the value as two digits.
     */
    private String pad(int value) {
        return String.format("%02d", value);
    }

    @Override
    public String toString() {
        return get24HourFormat();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Clock other = (Clock) obj;
        return hours == other.hours && 
               minutes == other.minutes && 
               seconds == other.seconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes, seconds);
    }
}