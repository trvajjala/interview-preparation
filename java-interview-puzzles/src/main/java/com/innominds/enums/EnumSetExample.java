package com.innominds.enums;

import java.util.EnumMap;
import java.util.EnumSet;

public class EnumSetExample {

    enum DAY {
        MON, TUE, WED, THU, FRI, SAT, SUN
    }

    public static void main(String[] args) {

        final EnumSet<DAY> days = EnumSet.of(DAY.MON, DAY.TUE, DAY.WED, DAY.THU);

        days.add(DAY.FRI);
        days.add(DAY.SUN);
        for (final DAY d : days) {
            System.out.println(d);
        }

        final EnumMap<DAY, String> map = new EnumMap<EnumSetExample.DAY, String>(EnumSetExample.DAY.class);

        map.put(DAY.MON, "Monday");

        System.out.println(map);
    }
}
