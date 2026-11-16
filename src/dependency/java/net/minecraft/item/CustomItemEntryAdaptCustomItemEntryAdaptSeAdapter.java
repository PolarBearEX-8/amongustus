package net.minecraft.item;

import client.util.HitBoxFilter;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class CustomItemEntryAdaptCustomItemEntryAdaptSeAdapter implements CustomItemEntryAdaptService {
   public CustomItemEntryAdaptCustomItemEntryAdaptSeAdapter(GoldenStaffEnumFacingSwitchMap goldenStaffEnumFacingSwitchMap) {
      this();
   }

   @Override
   public CustomItemEntryAdapter resolveCustomItemEntryAdapter(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      enumFacingController.enabled = true;
      enumFacingController.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()].enabled = true;
      return new HitBoxFilter(enumFacing, enumFacingController, random);
   }

   @Override
   public boolean matchesEnumFacingController(EnumFacingController enumFacingController) {
      return enumFacingController.enabledArray[EnumFacing.UP.getIndexOffset1()] && !enumFacingController.enumFacingControllerArray[EnumFacing.UP.getIndexOffset1()].enabled;
   }

   private CustomItemEntryAdaptCustomItemEntryAdaptSeAdapter() {
   }
}
