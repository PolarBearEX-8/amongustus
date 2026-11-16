package net.minecraft.item;

import client.util.FovQueue;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class CustomItemEntryAdaptCustomItemEntryAdaptSe implements CustomItemEntryAdaptService {
   @Override
   public boolean matchesEnumFacingController(EnumFacingController enumFacingController) {
      if (enumFacingController.enabledArray[EnumFacing.EAST.getIndexOffset1()]
         && !enumFacingController.enumFacingControllerArray[EnumFacing.EAST.getIndexOffset1()].enabled
         && enumFacingController.enabledArray[EnumFacing.UP.getIndexOffset1()]
         && !enumFacingController.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()].enabled) {
         EnumFacingController enumFacingController2 = enumFacingController.enumFacingControllerArray[EnumFacing.EAST.getIndexOffset1()];
         return enumFacingController2.enabledArray[EnumFacing.UP.getIndexOffset1()] && !enumFacingController2.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()].enabled;
      } else {
         return false;
      }
   }

   @Override
   public CustomItemEntryAdapter resolveCustomItemEntryAdapter(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      enumFacingController.enabled = true;
      enumFacingController.enumFacingControllerArray[EnumFacing.EAST.getIndexOffset1()].enabled = true;
      enumFacingController.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()].enabled = true;
      enumFacingController.enumFacingControllerArray[EnumFacing.EAST.getIndexOffset1()].enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()].enabled = true;
      return new FovQueue(enumFacing, enumFacingController, random);
   }

   public CustomItemEntryAdaptCustomItemEntryAdaptSe(GoldenStaffEnumFacingSwitchMap goldenStaffEnumFacingSwitchMap) {
      this();
   }

   private CustomItemEntryAdaptCustomItemEntryAdaptSe() {
   }
}
