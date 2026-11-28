package net.augustus.modules.modes.spider;

import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.movement.Spider;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class MotionSpiderMode extends AbstractSpiderMode implements TickListener {
   public final DoubleValue customMotion;
   public final DoubleValue tickDelay;

   public MotionSpiderMode(Spider spider, SettingGroup doubleValue) {
       super(spider, doubleValue, "Motion");
      this.customMotion = new DoubleValue(this.parentModule, "CustomMotion", 0.3, 0.0, 2.0, 3, this.modeSetting).visibleWhen(() -> this.isSelected());
      this.tickDelay = new DoubleValue(this.parentModule, "TickDelay", 0.0, 0.0, 20.0, 0, this.modeSetting).visibleWhen(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new MotionSpiderMode((Spider)obj, (SettingGroup)obj2);
   }

   @Override
   public void onTick() {
      if (this.canClimbWall()) {
         if ((double)mc.thePlayer.ticksExisted % (this.tickDelay.getValue() + 1.0) == 0.0) {
            mc.thePlayer.motionY = (double)((float)this.customMotion.getValue());
         }
      }
   }

}
