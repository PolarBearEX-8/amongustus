package net.minecraft.item;

import java.util.Random;
import net.minecraft.util.EnumFacing;

public class EnumFacingControllerCustomItemEntryAdaptSe implements CustomItemEntryAdaptService {
   @Override
   public boolean matchesEnumFacingController(EnumFacingController enumFacingController) {
      return enumFacingController.enabledArray[EnumFacing.EAST.getIndexOffset1()] && !enumFacingController.enumFacingControllerArray[EnumFacing.EAST.getIndexOffset1()].enabled;
   }

   @Override
   public CustomItemEntryAdapter resolveCustomItemEntryAdapter(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      enumFacingController.enabled = true;
      enumFacingController.enumFacingControllerArray[EnumFacing.EAST.getIndexOffset1()].enabled = true;
      return new GoldenFragment(enumFacing, enumFacingController, random);
   }

   private EnumFacingControllerCustomItemEntryAdaptSe() {
   }

   public EnumFacingControllerCustomItemEntryAdaptSe(GoldenStaffEnumFacingSwitchMap goldenStaffEnumFacingSwitchMap) {
      this();
   }
}
