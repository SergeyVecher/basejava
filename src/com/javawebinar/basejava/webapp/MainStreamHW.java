package com.javawebinar.basejava.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class MainStreamHW {

    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 2, 2, 3, 4, 4, 5, 1, 2, 4, 5, 3, 6, 8}));
        System.out.println(oddOrEven(List.of(1, 2, 2, 2, 3, 4, 4, 5, 1, 2, 4, 5, 3, 6, 8)));
    }

    private static int minValue(int[] values) {
        Objects.requireNonNull(values);
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (x, y) -> x * 10 + y);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Objects.requireNonNull(integers);
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        return (sum % 2 == 0) ?
                integers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList())
                : integers.stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
    }
}