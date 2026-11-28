package net.augustus.modules.modes.jesus;

import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.movement.Jesus;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;

public class SolidJesusMode extends AbstractJesusMode {
   public final BooleanValue userWaterBlockBounds;

   public SolidJesusMode(Jesus jesus, SettingGroup doubleValue) {
       super(jesus, doubleValue, "Solid");
      this.userWaterBlockBounds = (BooleanValue)new BooleanValue(this.parentModule, "UserWaterBlockBounds", false, this.modeSetting).withVisibility(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

}
