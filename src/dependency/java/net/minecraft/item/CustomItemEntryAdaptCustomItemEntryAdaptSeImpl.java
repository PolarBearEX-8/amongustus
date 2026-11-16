package net.minecraft.item;

import client.util.HitBoxChecker;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class CustomItemEntryAdaptCustomItemEntryAdaptSeImpl implements CustomItemEntryAdaptService {
   public CustomItemEntryAdaptCustomItemEntryAdaptSeImpl(GoldenStaffEnumFacingSwitchMap goldenStaffEnumFacingSwitchMap) {
      this();
   }

   @Override
   public CustomItemEntryAdapter resolveCustomItemEntryAdapter(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      EnumFacingController enumFacingController2 = enumFacingController;
      if (!enumFacingController.enabledArray[EnumFacing.NORTH.getIndexOffset1()] || enumFacingController.enumFacingControllerArray[EnumFacing.NORTH.getIndexOffset1()].enabled) {
         enumFacingController2 = enumFacingController.enumFacingControllerArray[EnumFacing.SOUTH.getIndexOffset1()];
      }

      enumFacingController2.enabled = true;
      enumFacingController2.enumFacingControllerArray[EnumFacing.NORTH.getIndexOffset1()].enabled = true;
      return new HitBoxChecker(enumFacing, enumFacingController2, random);
   }

   @Override
   public boolean matchesEnumFacingController(EnumFacingController enumFacingController) {
      return enumFacingController.enabledArray[EnumFacing.NORTH.getIndexOffset1()] && !enumFacingController.enumFacingControllerArray[EnumFacing.NORTH.getIndexOffset1()].enabled;
   }

   private CustomItemEntryAdaptCustomItemEntryAdaptSeImpl() {
   }
}
