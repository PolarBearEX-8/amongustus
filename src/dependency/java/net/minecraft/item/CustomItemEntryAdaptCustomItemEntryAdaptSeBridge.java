package net.minecraft.item;

import java.util.Random;
import net.minecraft.util.EnumFacing;

public class CustomItemEntryAdaptCustomItemEntryAdaptSeBridge implements CustomItemEntryAdaptService {
   @Override
   public CustomItemEntryAdapter resolveCustomItemEntryAdapter(EnumFacing enumFacing, EnumFacingController enumFacingController, Random random) {
      enumFacingController.enabled = true;
      return new IronSword(enumFacing, enumFacingController, random);
   }

   public CustomItemEntryAdaptCustomItemEntryAdaptSeBridge(GoldenStaffEnumFacingSwitchMap goldenStaffEnumFacingSwitchMap) {
      this();
   }

   private CustomItemEntryAdaptCustomItemEntryAdaptSeBridge() {
   }

   @Override
   public boolean matchesEnumFacingController(EnumFacingController enumFacingController) {
      return true;
   }
}
