package com.innominds.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSplitting {

    public static void main(String[] args) {

        final List<String> rowData = new ArrayList<>();
        rowData.add("1%%THIRU%%SOMEDESCRIPTION");
        rowData.add("2%%RAJU%%SOMEDESCRIPTION");
        rowData.add("3%%KIRAN%%SOME");
        rowData.add("4%%KIRAN%%JAMMAE");

        rowData.stream().skip(2).filter(item -> !item.startsWith("1")).map(item -> item.split("%%")).forEach((row) -> {

            Arrays.asList(row).forEach(cell -> {
                System.out.print(cell + " ");
            });

            System.out.println();

        });
    }
}
