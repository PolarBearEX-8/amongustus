package cc.baritone.utils;

import java.util.Arrays;

public class DepthFirstCoordinator implements SecondaryListener {
   public final SecondaryListener[] secondaryListenerArray;

   public SecondaryListener[] getSecondaryListenerArray() {
      return this.secondaryListenerArray;
   }

   @Override
   public double computeValue(int count, int count2, int count3) {
      double offset = Double.MAX_VALUE;

      for (SecondaryListener secondaryListener : this.secondaryListenerArray) {
         offset = Math.min(offset, secondaryListener.computeValue(count, count2, count3));
      }

      return offset;
   }

   @Override
   public int hashCode() {
      return Arrays.hashCode((Object[])this.secondaryListenerArray);
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) {
      SecondaryListener[] secondaryListenerArray2 = this.secondaryListenerArray;
      int length = secondaryListenerArray2.length;

      for (int index = 0; index < length; index++) {
         if (secondaryListenerArray2[index].checkIndex(count, count2, count3)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         DepthFirstCoordinator depthFirstCoordinator = (DepthFirstCoordinator)other;
         return Arrays.equals((Object[])this.secondaryListenerArray, (Object[])depthFirstCoordinator.secondaryListenerArray);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return "GoalComposite" + Arrays.toString((Object[])this.secondaryListenerArray);
   }

   public DepthFirstCoordinator(SecondaryListener[] secondaryListenerArray2) {
      this.secondaryListenerArray = secondaryListenerArray2;
   }

   @Override
   public double getValue() {
      double offset = Double.MAX_VALUE;

      for (SecondaryListener secondaryListener : this.secondaryListenerArray) {
         offset = Math.min(offset, secondaryListener.getValue());
      }

      return offset;
   }
}
