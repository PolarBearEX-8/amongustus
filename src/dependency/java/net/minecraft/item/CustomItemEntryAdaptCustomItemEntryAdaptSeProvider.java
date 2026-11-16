package net.minecraft.item;

import client.util.FovStack;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class CustomItemEntryAdaptCustomItemEntryAdaptSeProvider implements CustomItemEntryAdaptService {
   @Override
   public boolean matchesEnumFacingController(EnumFacingController enumFacingController) {
      return !enumFacingController.enabledArray[EnumFacing.WEST.getIndexOffset1()]
         && !enumFacingController.enabledArray[EnumFacing.EAST.getIndexOffset1()]
         && !enumFacingController.enabledArray[EnumFacing.NORTH.getIndexOffset1()]
         && !enumFacingController.enabledArray[EnumFacing.SOUTH.getIndexOffset1()]
         && !enumFacingController.enabledArray[EnumFacing.UP.getIndexOffset1()];
   }

   public CustomItemEntryAdaptCustomItemEntryAdaptSeProvider(GoldenStaffEnumFacingSwitchMap goldenStaffEnumFacingSwitchMap) {
      this();
   }

   private CustomItemEntryAdaptCustomItemEntryAdaptSeProvider() {
   }

   @Override
   public CustomItemEntryAdapter resolveCustomItemEntryAdapter(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      enumFacingController.enabled = true;
      return new FovStack(enumFacing, enumFacingController, random);
   }
}
