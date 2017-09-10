package com.ubs.opsit.interviews;

import java.util.function.Predicate;
import java.util.stream.IntStream;

public class TimeConverterImpl implements TimeConverter {

    @Override
    public String convertTime(String aTime) {
        StringBuilder clock = new StringBuilder();
        String[] time = aTime.split(":");

        int hours = Integer.valueOf(time[0]);
        int minutes = Integer.valueOf(time[1]);
        int seconds = Integer.valueOf(time[2]);

        int twoSecondYellowLight = (seconds % 2 == 0) ? 1 : 0;
        clock.append(switchOnOff(1, twoSecondYellowLight, i -> false));
        clock.append("\r\n");

        int fiveHoursRedLight = hours / 5;
        clock.append(switchOnOff(4, fiveHoursRedLight, i -> true));
        clock.append("\r\n");

        int oneHourRedLight = hours - (fiveHoursRedLight * 5);
        clock.append(switchOnOff(4, oneHourRedLight, i -> true));
        clock.append("\r\n");

        int fiveMinsYellowLight = minutes / 5;
        clock.append(switchOnOff(11, fiveMinsYellowLight, i -> (i == 3 || i == 6 || i == 9)));
        clock.append("\r\n");

        int oneMinYellowLight = minutes - (fiveMinsYellowLight * 5);
        clock.append(switchOnOff(4, oneMinYellowLight, i -> false));

        return clock.toString();
    }

    private String switchOnOff(int totalLightsInRow, int noOfLightsToTurnOn, Predicate<Integer> redYellowLightPredicate) {
        StringBuilder clockRow = new StringBuilder();

        IntStream.rangeClosed(1, totalLightsInRow)
                .forEach(i -> {
                    if (i <= noOfLightsToTurnOn) {
                        if (redYellowLightPredicate.test(i))
                            clockRow.append("R");
                        else
                            clockRow.append("Y");
                    } else {
                        clockRow.append("O");
                    }
                });
        return clockRow.toString();
    }

}

