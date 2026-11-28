package net.augustus.modules.world;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.WeaponFilterSettings;
import net.augustus.settings.BooleanValue;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class NoMiningTrace extends Module {
   public final BooleanValue alwaysAtRightClicking;
   public final BooleanValue alwaysAfterBlockBreakStarted;
   public final BooleanValue onlypickaxesWhenLeftclicking;
   public final WeaponFilterSettings excludedWeapons;

   public NoMiningTrace() {
       super("NoMiningTrace", "KeineBergbauspuren", new Color(66, 135, 245), Categorys.WORLD);
      this.alwaysAtRightClicking = (BooleanValue)(new BooleanValue(this, "AlwaysAtRightClicking", true)).markVisualOnly();
      this.alwaysAfterBlockBreakStarted = (BooleanValue)(new BooleanValue(this, "AlwaysAfterBlockBreakStarted", true)).markVisualOnly();
      this.onlypickaxesWhenLeftclicking = (BooleanValue)(new BooleanValue(this, "OnlyPickaxes when leftclicking", false)).markVisualOnly();
      this.excludedWeapons = (WeaponFilterSettings)(new WeaponFilterSettings(this, "Excluded weapons")).markAllVisualOnly();
      String str = "Rest";
      this.excludedWeapons.setOptionEnabled(str, false);
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
   }

   public boolean shouldIgnoreEntities() {
      if (this.isToggled()
         && (!this.onlypickaxesWhenLeftclicking.getValue() || mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemPickaxe)) {
         if (!this.excludedWeapons.isItemAllowed(mc.thePlayer.getCurrentEquippedItem()) || mc.playerController.isHittingBlock && this.alwaysAfterBlockBreakStarted.getValue()) {
            return true;
         }
      }

      return false;
   }


}
