package client.util;

import client.util.HelperContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class ClickHelper {
    public ArrayList arrayList;
    public int count;

    public double getValue6() {
        if (this.arrayList.size() == 0) {
            return Double.NaN;
        }
        double d = (Double)this.arrayList.get(0);
        Iterator iterator = this.arrayList.iterator();
        while (iterator.hasNext()) {
            double d2 = (Double)iterator.next();
            if (!(d > d2)) continue;
            d = d2;
        }
        return d;
    }

    public double[] resolveDoubleArray(ClickHelper clickHelper) {
        double d = this.getDouble11();
        double d2 = clickHelper.getDouble11();
        double d3 = 0.0;
        double d4 = 0.0;
        for (int i = 0; i < this.arrayList.size(); ++i) {
            d3 += ((Double)this.arrayList.get(i) - d) * (clickHelper.computeValue(i) - d2);
            d4 += ((Double)this.arrayList.get(i) - d) * ((Double)this.arrayList.get(i) - d);
        }
        double d5 = d3 / d4;
        double d6 = d2 - d5 * d;
        return new double[]{d5, d6};
    }

    public double computeValue(ClickHelper clickHelper) {
        long l = 0x2caef2bcc966L;
        long l2 = 0x2F407D47628BL ^ l ^ 0x27FE830B94EL;
        int n = this.arrayList.size();
        ArrayList arrayList = this.arrayList;
        double[] dArray = this.resolveDoubleArray(arrayList);
        ArrayList arrayList2 = clickHelper.getArrayList();
        double[] dArray2 = this.resolveDoubleArray(arrayList2);
        double d = 0.0;
        for (int i = 0; i < n; ++i) {
            double d2 = dArray[i] - dArray2[i];
            d += d2 * d2;
        }
        return 1.0 - 6.0 * d / (double)(n * (n * n - 1));
    }

    public int getCount() {
        return this.count;
    }

    public double computeValue(int n) {
        return (Double)this.arrayList.get(n);
    }

    public double getValue2() {
        double d = this.getDouble11();
        double d2 = 0.0;
        Iterator iterator = this.arrayList.iterator();
        while (iterator.hasNext()) {
            double d3 = (Double)iterator.next();
            double d4 = Math.abs(d - d3);
            d2 += d4;
        }
        return d2 / (double)this.arrayList.size();
    }

    public double getValueAsDouble() {
        double d = 0.0;
        double d2 = this.getDouble11();
        Iterator iterator = this.arrayList.iterator();
        while (iterator.hasNext()) {
            double d3 = (Double)iterator.next();
            d += Math.pow(d3 - d2, 2.0);
        }
        return Math.sqrt(d / (double)this.arrayList.size());
    }

    public void updateState() {
        double[] dArray = new double[this.arrayList.size()];
        for (int i = 0; i < dArray.length; ++i) {
            dArray[i] = (Double)this.arrayList.get(i);
        }
        HelperContext.applyDoubleArray(dArray);
        this.arrayList.clear();
        for (double d : dArray) {
            this.arrayList.add(d);
        }
    }

    public void clear() {
        this.arrayList.clear();
    }

    public double getValue() {
        double d = this.getDouble11();
        double d2 = 0.0;
        Iterator iterator = this.arrayList.iterator();
        while (iterator.hasNext()) {
            double d3 = (Double)iterator.next();
            d2 += Math.abs(d3 - d);
        }
        return d2 / (double)this.arrayList.size();
    }

    public ClickHelper resolveClickHelper(int n, int n2) {
        ClickHelper clickHelper = new ClickHelper(n2 - n + 1);
        for (int i = n; i <= n2; ++i) {
            double d = (Double)this.arrayList.get(i);
            clickHelper.processDouble(d);
        }
        return clickHelper;
    }

    public int getCountAsIndex() {
        ArrayList<Double> arrayList = new ArrayList<Double>();
        ArrayList arrayList2 = new ArrayList(this.arrayList);
        Collections.sort(arrayList2);
        int n = (int)Math.ceil((double)arrayList2.size() * 0.25);
        int n2 = (int)Math.floor((double)arrayList2.size() * 0.75);
        double d = (Double)arrayList2.get(n);
        double d2 = (Double)arrayList2.get(n2);
        double d3 = d2 - d;
        double d4 = d - 1.5 * d3;
        double d5 = d2 + 1.5 * d3;
        Iterator iterator = arrayList2.iterator();
        while (iterator.hasNext()) {
            double d6 = (Double)iterator.next();
            if (!(d6 < d4) && !(d6 > d5)) continue;
            arrayList.add(d6);
        }
        return arrayList.size();
    }

    public double getValue4() {
        double d = this.getDouble11();
        double d2 = 0.0;
        Iterator iterator = this.arrayList.iterator();
        while (iterator.hasNext()) {
            double d3 = (Double)iterator.next();
            d2 += (d3 - d) * (d3 - d);
        }
        return d2 / (double)this.arrayList.size();
    }

    public double[] getDoubleArray() {
        int n = this.arrayList.size();
        double[] dArray = new double[n];
        double d = this.getDouble11();
        for (int i = 0; i < n; ++i) {
            double d2 = 0.0;
            for (int j = 0; j < n - i; ++j) {
                d2 += ((Double)this.arrayList.get(j) - d) * ((Double)this.arrayList.get(j + i) - d);
            }
            dArray[i] = d2 / (double)(n - i);
        }
        return dArray;
    }

    public boolean canExecute() {
        return this.arrayList.size() == this.count;
    }

    public double computeValue3(ClickHelper clickHelper) {
        double d = this.getDouble11();
        double d2 = clickHelper.getDouble11();
        double d3 = 0.0;
        double d4 = 0.0;
        double d5 = 0.0;
        for (int i = 0; i < this.arrayList.size(); ++i) {
            double d6 = (Double)this.arrayList.get(i) - d;
            double d7 = clickHelper.computeValue(i) - d2;
            d3 += d6 * d7;
            d4 += d6 * d6;
            d5 += d7 * d7;
        }
        return d3 / Math.sqrt(d4 * d5);
    }

    public double getValue7() {
        double[] dArray = new double[this.arrayList.size()];
        for (int i = 0; i < this.arrayList.size(); ++i) {
            dArray[i] = (Double)this.arrayList.get(i);
        }
        HelperContext.applyDoubleArray(dArray);
        if (dArray.length % 2 == 0) {
            double d = dArray[dArray.length / 2];
            double d2 = dArray[dArray.length / 2 + 1];
            double d3 = Math.min(d, d2);
            double d4 = Math.max(d, d2);
            return d3 + (d4 - d3) / 2.0;
        }
        return dArray[dArray.length / 2 + 1];
    }

    private double[] resolveDoubleArray(ArrayList arrayList) {
        int n = arrayList.size();
        double[] dArray = new double[n];
        Object[] objectArray = arrayList.toArray(new Double[0]);
        Arrays.sort(objectArray);
        for (int i = 0; i < n; ++i) {
            dArray[i] = Arrays.asList(objectArray).indexOf(arrayList.get(i)) + 1;
        }
        return dArray;
    }

    public double[] resolveDoubleArrayByClickHelper(ClickHelper clickHelper) {
        int n = this.arrayList.size();
        if (n != clickHelper.size()) {
            return null;
        }
        double[] dArray = new double[n];
        double d = this.getDouble11();
        double d2 = clickHelper.getDouble11();
        for (int i = 0; i < n; ++i) {
            double d3 = 0.0;
            for (int j = 0; j < n - i; ++j) {
                int n2 = j + i;
                d3 += ((Double)this.arrayList.get(j) - d) * (clickHelper.computeValue(n2) - d2);
            }
            dArray[i] = d3 / (double)(n - i);
        }
        return dArray;
    }

    public void processDouble(double d) {
        this.arrayList.add(d);
        if (this.arrayList.size() > this.count) {
            this.arrayList.remove(0);
        }
    }

    public double getValue8() {
        double d = 0.0;
        double d2 = 0.0;
        for (int i = 0; i < this.arrayList.size(); ++i) {
            double d3 = (Double)this.arrayList.get(i);
            if (i > 0) {
                d2 += Math.abs(d - d3);
            }
            d = d3;
        }
        return d2 / (double)this.arrayList.size();
    }

    public boolean isActive() {
        double d = 0.0;
        for (int i = 0; i < this.getArrayList().size(); ++i) {
            double d2 = (Double)this.getArrayList().get(i);
            if (i > 0 && d != d2) {
                return false;
            }
            d = d2;
        }
        return true;
    }

    public double computeValue4(ClickHelper clickHelper) {
        if (this.arrayList.size() != clickHelper.size()) {
            return Double.NaN;
        }
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.arrayList.size() - 1; ++i) {
            for (int j = i + 1; j < this.arrayList.size(); ++j) {
                double d;
                double d2 = (Double)this.arrayList.get(i) - (Double)this.arrayList.get(j);
                if (d2 * (d = clickHelper.computeValue(i) - clickHelper.computeValue(j)) > 0.0) {
                    ++n;
                    continue;
                }
                if (!(d2 * d < 0.0)) continue;
                ++n2;
            }
        }
        return (double)(n - n2) / (double)(n + n2);
    }

    public double getValue5() {
        if (this.arrayList.size() == 0) {
            return Double.NaN;
        }
        double d = (Double)this.arrayList.get(0);
        Iterator iterator = this.arrayList.iterator();
        while (iterator.hasNext()) {
            double d2 = (Double)iterator.next();
            if (!(d < d2)) continue;
            d = d2;
        }
        return d;
    }

    public double computeValue2(ClickHelper clickHelper) {
        if (clickHelper.getArrayList().size() != this.arrayList.size()) {
            return Double.NaN;
        }
        double d = clickHelper.getValueAsDouble();
        double d2 = this.getValueAsDouble();
        return this.computeValueByClickHelper(clickHelper) / (d * d2);
    }

    public ArrayList getArrayList() {
        return this.arrayList;
    }

    public double[] resolveDoubleArray(double d) {
        double[] dArray = new double[this.arrayList.size()];
        dArray[0] = (Double)this.arrayList.get(0);
        for (int i = 1; i < this.arrayList.size(); ++i) {
            dArray[i] = d * (Double)this.arrayList.get(i) + (1.0 - d) * dArray[i - 1];
        }
        return dArray;
    }

    public double getDouble10() {
        double d = 0.0;
        Iterator iterator = this.arrayList.iterator();
        while (iterator.hasNext()) {
            double d2 = (Double)iterator.next();
            d += d2;
        }
        return d;
    }

    public double getDouble11() {
        return this.getDouble10() / (double)this.arrayList.size();
    }

    public int size() {
        return this.arrayList.size();
    }

    public double computeValueByClickHelper(ClickHelper clickHelper) {
        if (clickHelper.getArrayList().size() != this.arrayList.size()) {
            return Double.NaN;
        }
        double d = clickHelper.getDouble11();
        double d2 = this.getDouble11();
        double d3 = 0.0;
        for (int i = 0; i < this.arrayList.size(); ++i) {
            double d4 = (Double)this.arrayList.get(i) - d2;
            double d5 = (Double)clickHelper.getArrayList().get(i) - d;
            d3 += d4 * d5;
        }
        return d3 / (double)this.arrayList.size();
    }

    public double getValue3() {
        double d = this.getValue7();
        ArrayList<Double> arrayList = new ArrayList<Double>();
        Iterator iterator = this.arrayList.iterator();
        while (iterator.hasNext()) {
            double d2 = (Double)iterator.next();
            arrayList.add(Math.abs(d2 - d));
        }
        ClickHelper clickHelper = new ClickHelper(arrayList.size());
        Iterator iterator2 = arrayList.iterator();
        while (iterator2.hasNext()) {
            double d3 = (Double)iterator2.next();
            clickHelper.processDouble(d3);
        }
        return clickHelper.getValue7();
    }

    public ClickHelper(int n) {
        this.count = n;
        this.arrayList = new ArrayList(n);
    }
}

