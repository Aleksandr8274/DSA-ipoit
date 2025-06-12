package by.it.group410971.maleckij.lesson05;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_QSortOptimized {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_QSortOptimized.class.getResourceAsStream("dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        quickSort(segments, 0, segments.length - 1);

        for (int i = 0; i < m; i++) {
            int point = points[i];
            int first = binarySearch(segments, point);
            if (first == -1) {
                result[i] = 0;
                continue;
            }
            int count = 0;
            for (int j = first; j < segments.length && segments[j].start <= point; j++) {
                if (segments[j].stop >= point) {
                    count++;
                }
            }
            result[i] = count;
        }

        return result;
    }

    private void quickSort(Segment[] segments, int left, int right) {
        while (left < right) {
            int[] pivot = partition(segments, left, right);
            if (pivot[0] - left < right - pivot[1]) {
                quickSort(segments, left, pivot[0] - 1);
                left = pivot[1] + 1;
            } else {
                quickSort(segments, pivot[1] + 1, right);
                right = pivot[0] - 1;
            }
        }
    }

    private int[] partition(Segment[] segments, int left, int right) {
        int pivot = segments[left + (right - left) / 2].start;
        int i = left;
        int j = right;
        int k = left;

        while (k <= j) {
            if (segments[k].start < pivot) {
                swap(segments, i++, k++);
            } else if (segments[k].start > pivot) {
                swap(segments, k, j--);
            } else {
                k++;
            }
        }

        return new int[]{i, j};
    }

    private void swap(Segment[] segments, int i, int j) {
        Segment temp = segments[i];
        segments[i] = segments[j];
        segments[j] = temp;
    }

    private int binarySearch(Segment[] segments, int point) {
        int left = 0;
        int right = segments.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (segments[mid].start <= point) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    private class Segment implements Comparable<Segment> {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = Math.min(start, stop);
            this.stop = Math.max(start, stop);
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(this.start, o.start);
        }
    }
}