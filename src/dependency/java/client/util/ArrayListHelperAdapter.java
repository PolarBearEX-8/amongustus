package client.util;

import client.util.TimeHelper;
import java.util.ArrayList;

public class ArrayListHelperAdapter {
    public static double computeValueByArrayList(ArrayList arrayList) {
        return Math.sqrt(ArrayListHelperAdapter.computeValue2(arrayList));
    }

    public static double computeValue4(ArrayList arrayList) {
        double d = 0.0;
        for (int i = 0; i < arrayList.size(); ++i) {
            d += ((Double)arrayList.get(i)).doubleValue();
        }
        return d / (double)arrayList.size();
    }

    public static void applyArrayList(ArrayList arrayList, int n, int n2) {
        double d = (Double)arrayList.get(n);
        arrayList.set(n, arrayList.get(n2));
        arrayList.set(n2, d);
    }

    public static double computeValue2(ArrayList arrayList) {
        double d = ArrayListHelperAdapter.computeValue4(arrayList);
        double d2 = 0.0;
        for (int i = 0; i < arrayList.size(); ++i) {
            d2 += ((Double)arrayList.get(i) - d) * ((Double)arrayList.get(i) - d);
        }
        return d2 / (double)(arrayList.size() - 1);
    }

    public static int computeCount(ArrayList arrayList, int n, int n2) {
        double d = (Double)arrayList.get(n);
        int n3 = n;
        for (int i = n; i < n2; ++i) {
            if (!(d > (Double)arrayList.get(i))) continue;
            d = (Double)arrayList.get(i);
            n3 = i;
        }
        return n3;
    }

    public static double computeValue3(ArrayList arrayList) {
        ArrayListHelperAdapter.applyArrayList(arrayList);
        if (arrayList.size() % 2 == 0) {
            return ((Double)arrayList.get(arrayList.size() / 2 - 1) + (Double)arrayList.get(arrayList.size() / 2)) / 2.0;
        }
        return (Double)arrayList.get(arrayList.size() / 2);
    }

    public static void applyArrayList(ArrayList arrayList) {
        long l = 0xec2d0e55868L;
        int n = (int)((0x1F52CB06DC21L ^ l ^ 0x7A267BCF54B5L) >>> 56);
        for (int i = 0; i < arrayList.size(); ++i) {
            int n2 = arrayList.size();
            int n3 = i;
            int n4 = ArrayListHelperAdapter.computeCount(arrayList, n3, n2);
            ArrayListHelperAdapter.applyArrayList(arrayList, n4, i);
        }
    }

    public static double computeValue(ArrayList arrayList) {
        double d = (Double)arrayList.get(0);
        for (int i = 1; i < arrayList.size(); ++i) {
            if (!((Double)arrayList.get(i) > d)) continue;
            d = (Double)arrayList.get(i);
        }
        return d;
    }
}

