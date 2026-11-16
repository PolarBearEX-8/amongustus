package net.minecraft.item;

import net.minecraft.util.EnumFacing;

public class EnumFacingController {
   public int count;
   public EnumFacingController[] enumFacingControllerArray = new EnumFacingController[6];
   public boolean[] enabledArray = new boolean[6];
   public boolean enabled;
   public boolean enabled2;
   public int count2;

   public int getCount() {
      int count3 = 0;

      for (int index = 0; index < 6; index++) {
         if (this.enabledArray[index]) {
            count3++;
         }
      }

      return count3;
   }

   public boolean isActive() {
      return this.count >= 75;
   }

   public EnumFacingController(int count3) {
      this.count = count3;
   }

   public boolean checkIndex(int count3) {
      if (this.enabled2) {
         return true;
      } else {
         this.count2 = count3;

         for (int index = 0; index < 6; index++) {
            if (this.enumFacingControllerArray[index] != null
               && this.enabledArray[index]
               && this.enumFacingControllerArray[index].count2 != count3
               && this.enumFacingControllerArray[index].checkIndex(count3)) {
               return true;
            }
         }

         return false;
      }
   }

   public void applyEnumFacing(EnumFacing enumFacing, EnumFacingController enumFacingController) {
      this.enumFacingControllerArray[enumFacing.getIndexOffset1()] = enumFacingController;
      enumFacingController.enumFacingControllerArray[enumFacing.getEnumFacing5().getIndexOffset1()] = this;
   }

   public void updateState() {
      for (int index = 0; index < 6; index++) {
         this.enabledArray[index] = this.enumFacingControllerArray[index] != null;
      }
   }
}
