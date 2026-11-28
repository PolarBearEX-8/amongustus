package net.augustus.utils.math;

import java.util.ArrayList;
import java.util.Collections;

public class DoubleSampleWindow {
   public int maxSize;
   public ArrayList samples;

   public double pearsonCorrelation(DoubleSampleWindow doubleSampleWindow) {
      if (doubleSampleWindow.samples.size() != this.samples.size()) {
         return 0.0 / 0.0;
      } else {
         double d = doubleSampleWindow.stdDev();
         double d2 = this.stdDev();
         return this.covariance(doubleSampleWindow) / (d * d2);
      }
   }

   public double mean() {
      return this.sum() / (double)this.samples.size();
   }

   public double stdDev() {
      double d = 0.0;
      double d2 = this.mean();

      for (double d3 : (Iterable<Double>)(this.samples)) {
         d += Math.pow(d3 - d2, 2.0);
      }

      return Math.sqrt(d / (double)this.samples.size());
   }

   public int getMaxSize() {
      return this.maxSize;
   }

   public void clear() {
      this.samples.clear();
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

   public boolean containsWithinTolerance(double d, double d2) {

      for (int i = 0; i < this.samples.size(); i++) {
         if (Math.abs((Double)this.samples.get(i) - d) <= d2) {
            return true;
         }
      }

      return false;
   }

   public double meanAdjacentDifference() {
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

   public boolean containsExact(double d) {

      for (int i = 0; i < this.samples.size(); i++) {
         if ((Double)this.samples.get(i) == d) {
            return true;
         }
      }

      return false;
   }

   public double sum() {
      double d = 0.0;

      for (double d2 : (Iterable<Double>)(this.samples)) {
         d += d2;
      }

      return d;
   }

   public DoubleSampleWindow subWindow(int n, int n2) {
      DoubleSampleWindow doubleSampleWindow = new DoubleSampleWindow(n2 - n + 1);

      for (int i = n; i <= n2; i++) {
         double d = (Double)this.samples.get(i);
         doubleSampleWindow.add(d);
      }

      return doubleSampleWindow;
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

   public void add(double d) {
      this.samples.add(d);
      if (this.samples.size() > this.maxSize) {
         this.samples.remove(0);
      }
   }

   public double sumExcludingLast(int n) {
      if (this.samples.isEmpty()) {
         return 0.0;
      } else {
         double d = 0.0;

         for (int i = 0; i < this.samples.size() - n; i++) {
            d += ((Number)this.samples.get(i)).doubleValue();
         }

         return d;
      }
   }

   public boolean allEqual() {
      double d = 0.0;

      for (int i = 0; i < this.samples.size(); i++) {
         double d2 = (Double)this.samples.get(i);
         if (i > 0 && d != d2) {
            return false;
         }

         d = d2;
      }

      return true;
   }

   public double min() {
      if (this.samples.isEmpty()) {
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

   public double meanExcludingLast(int n) {
      return this.sumExcludingLast(n) / (double)Math.max(this.samples.size() - n, 1);
   }

   public boolean isFull() {
      return this.samples.size() == this.maxSize;
   }

   public DoubleSampleWindow(int n) {
      this.maxSize = n;
      this.samples = new ArrayList(n);
   }

   public ArrayList getSamples() {
      return this.samples;
   }

   public double covariance(DoubleSampleWindow doubleSampleWindow) {
      if (doubleSampleWindow.samples.size() != this.samples.size()) {
         return 0.0 / 0.0;
      } else {
         double d = doubleSampleWindow.mean();
         double d2 = this.mean();
         double d3 = 0.0;

         for (int i = 0; i < this.samples.size(); i++) {
            double d4 = (Double)this.samples.get(i) - d2;
            double d5 = (Double)doubleSampleWindow.samples.get(i) - d;
            d3 += d4 * d5;
         }

         return d3 / (double)this.samples.size();
      }
   }

   public double[] linearRegression() {
      double d = (double)this.samples.size() / 2.0 - 0.5;
      double d2 = this.mean();
      double d3 = 0.0;
      double d4 = 0.0;

      for (int i = 0; i < this.samples.size(); i++) {
         d3 += ((double)i - d) * (((Number)this.samples.get(i)).doubleValue() - d2);
         d4 += ((double)i - d) * ((double)i - d);
      }

      double d5 = d3 / d4;
      return new double[]{d5, d2 - d5 * d};
   }

   public double max() {
      if (this.samples.isEmpty()) {
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




}
