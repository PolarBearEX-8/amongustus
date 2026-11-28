package net.augustus.utils.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StatisticalSampleBuffer {
   public int maxSize;
   public ArrayList samples;

   public int getMaxSize() {
      return this.maxSize;
   }

   public double covariance(StatisticalSampleBuffer statisticalSampleBuffer) {
      if (statisticalSampleBuffer.getSamples().size() != this.samples.size()) {
         return 0.0 / 0.0;
      } else {
         double d = statisticalSampleBuffer.mean();
         double d2 = this.mean();
         double d3 = 0.0;

         for (int i = 0; i < this.samples.size(); i++) {
            double d4 = (Double)this.samples.get(i) - d2;
            double d5 = (Double)statisticalSampleBuffer.getSamples().get(i) - d;
            d3 += d4 * d5;
         }

         return d3 / (double)this.samples.size();
      }
   }

   public StatisticalSampleBuffer subBuffer(int n, int n2) {
      StatisticalSampleBuffer statisticalSampleBuffer = new StatisticalSampleBuffer(n2 - n + 1);

      for (int i = n; i <= n2; i++) {
         double d = (Double)this.samples.get(i);
         statisticalSampleBuffer.add(d);
      }

      return statisticalSampleBuffer;
   }

   public double mean() {
      return this.sum() / (double)this.samples.size();
   }

   public int size() {
      return this.samples.size();
   }

   public boolean allEqual() {
      double d = 0.0;

      for (int i = 0; i < this.getSamples().size(); i++) {
         double d2 = (Double)this.getSamples().get(i);
         if (i > 0 && d != d2) {
            return false;
         }

         d = d2;
      }

      return true;
   }

   public void sort() {
      double[] doubles = new double[this.samples.size()];

      for (int i = 0; i < doubles.length; i++) {
         doubles[i] = (Double)this.samples.get(i);
      }

      DoubleHeapSort.sort(doubles);
      this.samples.clear();

      for (double d : doubles) {
         this.samples.add(d);
      }
   }

   public double spearmanCorrelation(StatisticalSampleBuffer statisticalSampleBuffer) {
      int size = this.samples.size();
      double[] doubles = this.computeRanks(this.samples);
      double[] doubles2 = this.computeRanks(statisticalSampleBuffer.getSamples());
      double d = 0.0;

      for (int i = 0; i < size; i++) {
         double d2 = doubles[i] - doubles2[i];
         d += d2 * d2;
      }

      return 1.0 - 6.0 * d / (double)(size * (size * size - 1));
   }

   public double[] crossCovariance(StatisticalSampleBuffer statisticalSampleBuffer) {
      int size = this.samples.size();
      if (size != statisticalSampleBuffer.size()) {
         return null;
      } else {
         double[] doubles = new double[size];
         double d = this.mean();
         double d2 = statisticalSampleBuffer.mean();

         for (int i = 0; i < size; i++) {
            double d3 = 0.0;

            for (int j = 0; j < size - i; j++) {
               double d4 = (Double)this.samples.get(j) - d;
               int n = j + i;
               d3 += d4 * (statisticalSampleBuffer.get(n) - d2);
            }

            doubles[i] = d3 / (double)(size - i);
         }

         return doubles;
      }
   }

   public double pearsonCorrelationDirect(StatisticalSampleBuffer statisticalSampleBuffer) {
      double d = this.mean();
      double d2 = statisticalSampleBuffer.mean();
      double d3 = 0.0;
      double d4 = 0.0;
      double d5 = 0.0;

      for (int i = 0; i < this.samples.size(); i++) {
         double d6 = (Double)this.samples.get(i) - d;
         double d7 = statisticalSampleBuffer.get(i) - d2;
         d3 += d6 * d7;
         d4 += d6 * d6;
         d5 += d7 * d7;
      }

      return d3 / Math.sqrt(d4 * d5);
   }

   public int countIqrOutliers() {
      ArrayList list = new ArrayList();
      ArrayList list2 = new ArrayList(this.samples);
      Collections.sort(list2);
      int n = (int)Math.ceil((double)list2.size() * 0.25);
      int n2 = (int)Math.floor((double)list2.size() * 0.75);
      double d = (Double)list2.get(n);
      double d2 = (Double)list2.get(n2);
      double d3 = d2 - d;
      double d4 = d - 1.5 * d3;
      double d5 = d2 + 1.5 * d3;

      for (double d6 : (Iterable<Double>)(list2)) {
         if (d6 < d4 || d6 > d5) {
            list.add(d6);
         }
      }

      return list.size();
   }

   public void clear() {
      this.samples.clear();
   }

   public StatisticalSampleBuffer(int n) {
      this.maxSize = n;
      this.samples = new ArrayList(n);
   }

   public double sum() {
      double d = 0.0;

      for (double d2 : (Iterable<Double>)(this.samples)) {
         d += d2;
      }

      return d;
   }

   public double min() {
      if (this.samples.size() == 0) {
         return 0.0 / 0.0;
      } else {
         double d = (Double)this.samples.get(0);

         for (Double d2 : (Iterable<Double>)(this.samples)) {
            double d3 = d2;
            if (d > d3) {
               d = d3;
            }
         }

         return d;
      }
   }

   public double variance() {
      double d = this.mean();
      double d2 = 0.0;

      for (double d3 : (Iterable<Double>)(this.samples)) {
         d2 += (d3 - d) * (d3 - d);
      }

      return d2 / (double)this.samples.size();
   }

   public boolean isFull() {
      return this.samples.size() == this.maxSize;
   }

   public double get(int n) {
      return (Double)this.samples.get(n);
   }

   public ArrayList getSamples() {
      return this.samples;
   }

   public double max() {
      if (this.samples.size() == 0) {
         return 0.0 / 0.0;
      } else {
         double d = (Double)this.samples.get(0);

         for (Double d2 : (Iterable<Double>)(this.samples)) {
            double d3 = d2;
            if (d < d3) {
               d = d3;
            }
         }

         return d;
      }
   }

   public double[] linearRegression(StatisticalSampleBuffer statisticalSampleBuffer) {
      double d = this.mean();
      double d2 = statisticalSampleBuffer.mean();
      double d3 = 0.0;
      double d4 = 0.0;

      for (int i = 0; i < this.samples.size(); i++) {
         d3 += (((Number)this.samples.get(i)).doubleValue() - d) * (statisticalSampleBuffer.get(i) - d2);
         d4 += (((Number)this.samples.get(i)).doubleValue() - d) * (((Number)this.samples.get(i)).doubleValue() - d);
      }

      double d5 = d3 / d4;
      double d6 = d2 - d5 * d;
      return new double[]{d5, d6};
   }

   public double meanAbsoluteDifference() {
      double d = 0.0;
      double d2 = 0.0;

      for (int i = 0; i < this.samples.size(); i++) {
         double d3 = (Double)this.samples.get(i);
         if (i > 0) {
            d2 += Math.abs(d - d3);
         }

         d = d3;
      }

      return d2 / (double)this.samples.size();
   }

   public double kendallTau(StatisticalSampleBuffer statisticalSampleBuffer) {
      if (this.samples.size() != statisticalSampleBuffer.size()) {
         return 0.0 / 0.0;
      } else {
         int n3 = 0;
         int n4 = 0;

         for (int i = 0; i < this.samples.size() - 1; i++) {
            for (int j = i + 1; j < this.samples.size(); j++) {
               double d = (Double)this.samples.get(i) - (Double)this.samples.get(j);
               double d2 = statisticalSampleBuffer.get(i) - statisticalSampleBuffer.get(j);
               if (d * d2 > 0.0) {
                  n3++;
               } else if (d * d2 < 0.0) {
                  n4++;
               }
            }
         }

         return (double)(n3 - n4) / (double)(n3 + n4);
      }
   }

   public double meanAbsoluteDeviation() {
      double d = this.mean();
      double d2 = 0.0;

      for (double d3 : (Iterable<Double>)(this.samples)) {
         double d4 = Math.abs(d - d3);
         d2 += d4;
      }

      return d2 / (double)this.samples.size();
   }

   public void add(double d) {
      this.samples.add(d);
      if (this.samples.size() > this.maxSize) {
         this.samples.remove(0);
      }
   }

   private double[] computeRanks(ArrayList list) {
      int size = list.size();
      double[] doubles = new double[size];
      Double[] doubles2 = (Double[])list.toArray(new Double[0]);
      Arrays.sort((Object[])doubles2);

      for (int i = 0; i < size; i++) {
         doubles[i] = (double)(Arrays.asList(doubles2).indexOf(list.get(i)) + 1);
      }

      return doubles;
   }

   public double median() {
      double[] doubles = new double[this.samples.size()];

      for (int i = 0; i < this.samples.size(); i++) {
         doubles[i] = (Double)this.samples.get(i);
      }

      DoubleHeapSort.sort(doubles);
      if (doubles.length % 2 == 0) {
         double d = doubles[doubles.length / 2];
         double d2 = doubles[doubles.length / 2 + 1];
         double d3 = Math.min(d, d2);
         return d3 + (Math.max(d, d2) - d3) / 2.0;
      } else {
         return doubles[doubles.length / 2 + 1];
      }
   }

   public double averageAbsoluteDeviation() {
      double d = this.mean();
      double d2 = 0.0;

      for (double d3 : (Iterable<Double>)(this.samples)) {
         d2 += Math.abs(d3 - d);
      }

      return d2 / (double)this.samples.size();
   }

   public double[] autocovariance() {
      int size = this.samples.size();
      double[] doubles = new double[size];
      double d = this.mean();

      for (int i = 0; i < size; i++) {
         double d2 = 0.0;

         for (int j = 0; j < size - i; j++) {
            d2 += (((Number)this.samples.get(j)).doubleValue() - d) * (((Number)this.samples.get(j + i)).doubleValue() - d);
         }

         doubles[i] = d2 / (double)(size - i);
      }

      return doubles;
   }

   public double pearsonCorrelation(StatisticalSampleBuffer statisticalSampleBuffer) {
      if (statisticalSampleBuffer.getSamples().size() != this.samples.size()) {
         return 0.0 / 0.0;
      } else {
         double d = statisticalSampleBuffer.stdDev();
         double d2 = this.stdDev();
         return this.covariance(statisticalSampleBuffer) / (d * d2);
      }
   }

   public double stdDev() {
      double d = 0.0;
      double d2 = this.mean();

      for (double d3 : (Iterable<Double>)(this.samples)) {
         d += Math.pow(d3 - d2, 2.0);
      }

      return Math.sqrt(d / (double)this.samples.size());
   }

   public double medianAbsoluteDeviation() {
      double d = this.median();
      ArrayList list = new ArrayList();

      for (double d2 : (Iterable<Double>)(this.samples)) {
         list.add(Math.abs(d2 - d));
      }

      StatisticalSampleBuffer statisticalSampleBuffer = new StatisticalSampleBuffer(list.size());

      for (double d3 : (Iterable<Double>)(list)) {
         statisticalSampleBuffer.add(d3);
      }

      return statisticalSampleBuffer.median();
   }

   public double[] exponentialMovingAverage(double d) {
      double[] doubles = new double[this.samples.size()];
      doubles[0] = (Double)this.samples.get(0);

      for (int i = 1; i < this.samples.size(); i++) {
         doubles[i] = d * (Double)this.samples.get(i) + (1.0 - d) * doubles[i - 1];
      }

      return doubles;
   }




}
