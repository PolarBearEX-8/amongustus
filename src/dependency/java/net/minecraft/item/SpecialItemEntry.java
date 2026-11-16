package net.minecraft.item;

import client.setting.ItemPhysicsNoneType;
import client.util.SplineAccumulator;
import net.minecraft.client.renderer.GlStateManager;

public class SpecialItemEntry {
   public static final SpecialItemEntry specialItemEntry = new SpecialItemEntry();
   public static float factor9 = 0.0F;
   public static float factor6 = 0.0F;
   public static float factor5 = 0.0F;
   public static float factor7 = 0.0F;
   public static float factor = 0.0F;
   public static float factor2 = 0.0F;
   public static float factor8 = 0.0F;
   public static float factor4 = 0.0F;
   public static float factor3 = 0.0F;
   public final AuxiliaryItemEntry auxiliaryItemEntry4;
   public final AuxiliaryItemEntry auxiliaryItemEntry;
   public final AuxiliaryItemEntry auxiliaryItemEntry5;
   public final AuxiliaryItemEntry auxiliaryItemEntry3;
   public final AuxiliaryItemEntry auxiliaryItemEntry2;
   public final AuxiliaryItemEntry auxiliaryItemEntry6;

   public void applyItemPhysicsNoneType(ItemPhysicsNoneType itemPhysicsNoneType) {
      AuxiliaryItemEntry auxiliaryItemEntry7 = this.getAuxiliaryItemEntry(itemPhysicsNoneType);
      if (auxiliaryItemEntry7 != AuxiliaryItemEntry.auxiliaryItemEntry) {
         GlStateManager.translate(auxiliaryItemEntry7.vector3f.x + factor9, auxiliaryItemEntry7.vector3f.y + factor6, auxiliaryItemEntry7.vector3f.z + factor5);
         GlStateManager.rotate(auxiliaryItemEntry7.vector3f2.y + factor, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(auxiliaryItemEntry7.vector3f2.x + factor7, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(auxiliaryItemEntry7.vector3f2.z + factor2, 0.0F, 0.0F, 1.0F);
         GlStateManager.translate3f3(auxiliaryItemEntry7.vector3f3.x + factor8, auxiliaryItemEntry7.vector3f3.y + factor4, auxiliaryItemEntry7.vector3f3.z + factor3);
      }
   }

   public boolean matchesItemPhysicsNoneType(ItemPhysicsNoneType itemPhysicsNoneType) {
      return !this.getAuxiliaryItemEntry(itemPhysicsNoneType).equals(AuxiliaryItemEntry.auxiliaryItemEntry);
   }

   public AuxiliaryItemEntry getAuxiliaryItemEntry(ItemPhysicsNoneType itemPhysicsNoneType) {
      switch (SplineAccumulator.countArray[itemPhysicsNoneType.ordinal()]) {
         case 1:
            return this.auxiliaryItemEntry4;
         case 2:
            return this.auxiliaryItemEntry;
         case 3:
            return this.auxiliaryItemEntry5;
         case 4:
            return this.auxiliaryItemEntry3;
         case 5:
            return this.auxiliaryItemEntry2;
         case 6:
            return this.auxiliaryItemEntry6;
         default:
            return AuxiliaryItemEntry.auxiliaryItemEntry;
      }
   }

   private SpecialItemEntry() {
      this(
         AuxiliaryItemEntry.auxiliaryItemEntry,
         AuxiliaryItemEntry.auxiliaryItemEntry,
         AuxiliaryItemEntry.auxiliaryItemEntry,
         AuxiliaryItemEntry.auxiliaryItemEntry,
         AuxiliaryItemEntry.auxiliaryItemEntry,
         AuxiliaryItemEntry.auxiliaryItemEntry
      );
   }

   public SpecialItemEntry(
      AuxiliaryItemEntry auxiliaryItemEntry7, AuxiliaryItemEntry auxiliaryItemEntry8, AuxiliaryItemEntry auxiliaryItemEntry9, AuxiliaryItemEntry auxiliaryItemEntry10, AuxiliaryItemEntry auxiliaryItemEntry11, AuxiliaryItemEntry auxiliaryItemEntry12
   ) {
      this.auxiliaryItemEntry4 = auxiliaryItemEntry7;
      this.auxiliaryItemEntry = auxiliaryItemEntry8;
      this.auxiliaryItemEntry5 = auxiliaryItemEntry9;
      this.auxiliaryItemEntry3 = auxiliaryItemEntry10;
      this.auxiliaryItemEntry2 = auxiliaryItemEntry11;
      this.auxiliaryItemEntry6 = auxiliaryItemEntry12;
   }

   public SpecialItemEntry(SpecialItemEntry specialItemEntry2) {
      this.auxiliaryItemEntry4 = specialItemEntry2.auxiliaryItemEntry4;
      this.auxiliaryItemEntry = specialItemEntry2.auxiliaryItemEntry;
      this.auxiliaryItemEntry5 = specialItemEntry2.auxiliaryItemEntry5;
      this.auxiliaryItemEntry3 = specialItemEntry2.auxiliaryItemEntry3;
      this.auxiliaryItemEntry2 = specialItemEntry2.auxiliaryItemEntry2;
      this.auxiliaryItemEntry6 = specialItemEntry2.auxiliaryItemEntry6;
   }
}
