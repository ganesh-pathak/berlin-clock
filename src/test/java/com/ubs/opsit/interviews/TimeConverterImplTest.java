package com.ubs.opsit.interviews;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TimeConverterImplTest {
    TimeConverter clock = new TimeConverterImpl();

    @Test
    public void shouldSwitchOnTopLampAfterEveryTwoSeconds() {
        assertThat(clock.convertTime("13:25:02").charAt(0), is('Y'));
        assertThat(clock.convertTime("13:25:03").charAt(0), is('O'));
        assertThat(clock.convertTime("13:25:04").charAt(0), is('Y'));
    }

    @Test
    public void shouldSwitchOnFirstRowFiveHoursLights() {
        assertThat(clock.convertTime("13:25:02").substring(3, 7), is("RROO"));
        assertThat(clock.convertTime("09:13:02").substring(3, 7), is("ROOO"));
        assertThat(clock.convertTime("22:05:02").substring(3, 7), is("RRRR"));
    }

    @Test
    public void shouldSwitchOnSecondRowOneHourLights() {
        assertThat(clock.convertTime("13:25:02").substring(9, 13), is("RRRO"));
        assertThat(clock.convertTime("09:13:02").substring(9, 13), is("RRRR"));
        assertThat(clock.convertTime("22:05:02").substring(9, 13), is("RROO"));
    }

    @Test
    public void shouldSwitchOnThirdRowFiveMinutesLights() {
        assertThat(clock.convertTime("13:25:02").substring(15, 26), is("YYRYYOOOOOO"));
        assertThat(clock.convertTime("09:13:02").substring(15, 26), is("YYOOOOOOOOO"));
        assertThat(clock.convertTime("22:05:02").substring(15, 26), is("YOOOOOOOOOO"));
    }

    @Test
    public void shouldSwitchOnThirdRowFiveMinutesLightsAfterSecondHalf() {
        assertThat(clock.convertTime("13:25:02").substring(15, 26), is("YYRYYOOOOOO"));
        assertThat(clock.convertTime("09:51:02").substring(15, 26), is("YYRYYRYYRYO"));
        assertThat(clock.convertTime("22:32:02").substring(15, 26), is("YYRYYROOOOO"));
    }

    @Test
    public void shouldSwitchOnFourthRowOneMinutesLights() {
        assertThat(clock.convertTime("13:25:02").substring(28, 32), is("OOOO"));
        assertThat(clock.convertTime("09:13:02").substring(28, 32), is("YYYO"));
        assertThat(clock.convertTime("22:06:02").substring(28, 32), is("YOOO"));
    }

}