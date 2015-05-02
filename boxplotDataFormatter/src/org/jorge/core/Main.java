package org.jorge.core;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(final String[] args) {
        final StringTokenizer tokenizer = new StringTokenizer(args[0], ",");
        if (tokenizer.countTokens() < 3)
            throw new IllegalArgumentException("Too few datapoints");
        final Double[] numbers = new Double[tokenizer.countTokens()];
        Integer i = 0;
        while (tokenizer.hasMoreTokens()) {
            numbers[i] = Double.parseDouble(tokenizer.nextToken());
            i++;
        }
        quicksort(numbers, 0, numbers.length - 1);
        System.out.println("Assuming outliers are already taken out...");
        System.out.println("Dataset: " + Arrays.toString(numbers));
        System.out.println("Dataset length: " + numbers.length);
        System.out.println("Bottom whisker: " + numbers[0]);
        final Boolean isEven = numbers.length % 2 == 0;
        if (numbers.length > 3) {
            System.out.println("Q3 (box bottom): " + middle(Arrays.copyOfRange(numbers, 0, isEven ? numbers.length / 2 : numbers.length / 2 - 1)));
        } else System.out.println("Q3 (box bottom): " + numbers[0]);
        System.out.println("Median (Q2, box line): " + median(numbers));
        if (numbers.length > 3) {
            System.out.println("Q1 (box top): " + middle(Arrays.copyOfRange(numbers, isEven ? numbers.length / 2 : numbers.length / 2 + 1, numbers.length - 1)));
        } else System.out.println("Q1 (box top): " + numbers[2]);
        System.out.println("Top whisker: " + numbers[numbers.length - 1]);
    }

    private static Double middle(Double[] numbers) {
        if (numbers.length % 2 == 0) {
            return median(new Double[]{numbers[numbers.length / 2], numbers[numbers.length / 2 - 1]});
        } else {
            return numbers[numbers.length / 2];
        }
    }

    private static Double median(Double[] numbers) {
        Double acc = 0D;
        for (Integer i = 0; i < numbers.length; i++)
            acc += numbers[i];

        return acc / numbers.length;
    }

    private static void quicksort(Double[] arr, final Integer low, final Integer high) {
        Integer i = low, j = high;
        final Double pivot = arr[low + (high - low) / 2];

        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        if (low < j) {
            quicksort(arr, low, j);
        }
        if (high > i) {
            quicksort(arr, i, high);
        }
    }

    private static void swap(Double[] array, final Integer a, final Integer b) {
        final Double temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
