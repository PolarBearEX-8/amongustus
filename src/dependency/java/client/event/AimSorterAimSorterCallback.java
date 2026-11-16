package client.event;

import client.util.AimSorter;
import java.util.ArrayList;
import java.util.Arrays;

public class AimSorterAimSorterCallback implements AimSorterCallback {
   public AimSorter[] aimSorterArray;
   public AimSorter[] aimSorterArray2;
   public AimSorter[] aimSorterArray3;
   public int count = Integer.MAX_VALUE;
   public static final AimSorter[] aimSorterArray4 = new AimSorter[0];

   @Override
   public AimSorter[] resolveAimSorterArray(PreUpdateObserver[] preUpdateObserverArray) {
      int count2 = this.aimSorterArray.length + this.aimSorterArray3.length;
      int count3 = preUpdateObserverArray.length - count2;
      int count4 = 0;

      for (int index = 0;
         index + this.aimSorterArray2.length <= count3 && count2 + index + this.aimSorterArray2.length <= this.count;
         index += this.aimSorterArray2.length
      ) {
         count4++;
      }

      ArrayList list = new ArrayList();
      list.addAll(Arrays.asList(this.aimSorterArray));

      for (int index2 = 0; index2 < count4; index2++) {
         list.addAll(Arrays.asList(this.aimSorterArray2));
      }

      list.addAll(Arrays.asList(this.aimSorterArray3));
      return (AimSorter[])list.toArray(new AimSorter[list.size()]);
   }

   public AimSorter[] getAimSorterArray() {
      return this.aimSorterArray;
   }

   private static AimSorter[] resolveAimSorterArray(AimSorter[] aimSorterArray5) {
      return aimSorterArray5 == null ? aimSorterArray4 : aimSorterArray5;
   }

   public AimSorterAimSorterCallback resolveAimSorterAimSorterCallback(int count2) {
      return new AimSorterAimSorterCallback(this.aimSorterArray, this.aimSorterArray2, this.aimSorterArray3, count2);
   }

   public AimSorterAimSorterCallback(AimSorter[] aimSorterArray5, AimSorter[] aimSorterArray6, AimSorter[] aimSorterArray7) {
      this(aimSorterArray5, aimSorterArray6, aimSorterArray7, Integer.MAX_VALUE);
   }

   public AimSorterAimSorterCallback resolveAimSorterAimSorterCallbackByAimSorterArray(AimSorter[] aimSorterArray5) {
      return new AimSorterAimSorterCallback(this.aimSorterArray, aimSorterArray5, this.aimSorterArray3);
   }

   public AimSorter[] getAimSorterArray2() {
      return this.aimSorterArray2;
   }

   public AimSorterAimSorterCallback() {
      this((AimSorter[])null, (AimSorter[])null, (AimSorter[])null);
   }

   public AimSorterAimSorterCallback(AimSorter[] aimSorterArray5, AimSorter[] aimSorterArray6, AimSorter[] aimSorterArray7, int count2) {
      this.aimSorterArray = resolveAimSorterArray(aimSorterArray5);
      this.aimSorterArray2 = resolveAimSorterArray(aimSorterArray6);
      this.aimSorterArray3 = resolveAimSorterArray(aimSorterArray7);
      this.count = count2;
   }

   public int getCount() {
      return this.aimSorterArray == null ? 0 : this.aimSorterArray.length;
   }

   public AimSorterAimSorterCallback resolveAimSorterAimSorterCallback2(AimSorter[] aimSorterArray5) {
      return new AimSorterAimSorterCallback(this.aimSorterArray, this.aimSorterArray2, aimSorterArray5);
   }

   public AimSorter[] getAimSorterArray3() {
      return this.aimSorterArray3;
   }

   public AimSorterAimSorterCallback resolveAimSorterAimSorterCallback(AimSorter[] aimSorterArray5) {
      return new AimSorterAimSorterCallback(aimSorterArray5, this.aimSorterArray2, this.aimSorterArray3);
   }
}
