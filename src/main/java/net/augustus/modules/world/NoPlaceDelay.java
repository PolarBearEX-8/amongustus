package net.augustus.modules.world;

import java.awt.Color;
import java.util.HashMap;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.settings.options.NoPlaceDelayFacingOption;
import net.minecraft.util.EnumFacing;

public class NoPlaceDelay extends Module {
   public final MultiOptionSetting onlyOnTheseBlockFaces;
   private static final HashMap FACING_OPTIONS = new HashMap();

   public NoPlaceDelay() {
       super("NoPlaceDelay", "OhnePlazierungsDelay", new Color(235, 64, 52), Categorys.WORLD);
      NoPlaceDelayFacingOption[] noPlaceDelayFacingOptions = new NoPlaceDelayFacingOption[6];

      for (int i = 0; i < noPlaceDelayFacingOptions.length; i++) {
         noPlaceDelayFacingOptions[i] = new NoPlaceDelayFacingOption(EnumFacing.getValues()[i], true);
         FACING_OPTIONS.put(EnumFacing.getValues()[i], noPlaceDelayFacingOptions[i]);
      }

      ((NoPlaceDelayFacingOption)FACING_OPTIONS.get(EnumFacing.UP)).setSelected(false);
      this.onlyOnTheseBlockFaces = new MultiOptionSetting(this, "Only on these block faces", noPlaceDelayFacingOptions);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
   }

   public static boolean isFacingEnabled(EnumFacing facing) {
      return ((NoPlaceDelayFacingOption)FACING_OPTIONS.get(facing)).isSelected();
   }

}
