package net.augustus.modules.modes.spider;

import net.augustus.events.EventMotion;
import net.augustus.events.listeners.MotionListener;
import java.util.function.BiFunction;
import net.augustus.modules.movement.Spider;
import net.augustus.settings.SettingGroup;

public class VerusSpiderMode extends AbstractSpiderMode implements MotionListener {

   public VerusSpiderMode(Spider spider, SettingGroup doubleValue) {
      super(spider, doubleValue, "Verus");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(18);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new VerusSpiderMode((Spider)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (this.canClimbWall()) {
         if (mc.thePlayer.isCollidedHorizontally && mc.thePlayer.ticksExisted % 4 != 0) {
            mc.thePlayer.motionY = 0.5;
         }
      }
   }

}
