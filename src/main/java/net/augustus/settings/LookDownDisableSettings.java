package net.augustus.settings;

import net.augustus.ClientManagers;
import net.augustus.modules.Module;
import net.augustus.utils.interfaces.MC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class LookDownDisableSettings extends SettingComposite implements MC {
   private final StringValue disableWhileLookingDown;
   private final DoubleValue lookDownPitch;

   private boolean isHoldingBlock() {
      if (mc.thePlayer == null) {
         return false;
      } else {
         ItemStack currentEquippedItem = mc.thePlayer.getCurrentEquippedItem();
         if (currentEquippedItem == null) {
            return false;
         } else {
            return currentEquippedItem.getItem() instanceof ItemBlock;
         }
      }
   }

   private boolean isPitchSettingVisible() {
      return !this.disableWhileLookingDown.getSelectedValue().equals("None");
   }

   public LookDownDisableSettings(Module module, String str, SettingGroup doubleValue) {
       super();
      this.disableWhileLookingDown = (StringValue)this.registerSetting(new StringValue(
         module, "_DisableWhileLookingDown" + str, "None", new String[]{"None", "OnlyHoldingBlock", "Always"}, doubleValue
      ).withDisplayName("DisableWhileLookingDown"));
      this.lookDownPitch = (DoubleValue)this.registerSetting(new DoubleValue(module, "_LookDownPitch" + str, 70.0, 0.0, 90.0, 0, doubleValue).named("LookDownPitch").visibleWhen(() -> this.isPitchSettingVisible()));
   }

   public LookDownDisableSettings(Module module, String str) {
       this(module, str, null);
   }

   public boolean shouldDisable() {
      switch (this.disableWhileLookingDown.getSelectedValue()) {
         case "None":
            return false;
         case "OnlyHoldingBlock":
            return this.isPitchBelowThreshold() && this.isHoldingBlock();
         default:
            return this.isPitchBelowThreshold();
      }
   }

   public LookDownDisableSettings(Module module) {
       this(module, "", null);
   }

   private boolean isPitchBelowThreshold() {
      return (double)ClientManagers.rotationController.getPreviousRotation().pitch < this.lookDownPitch.getValue();
   }

   public LookDownDisableSettings(Module module, SettingGroup doubleValue) {
       this(module, "", doubleValue);
   }


}
