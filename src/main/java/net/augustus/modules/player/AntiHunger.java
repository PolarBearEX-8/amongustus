package net.augustus.modules.player;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.MotionListener;
import net.augustus.events.EventMotion;

public class AntiHunger extends Module implements MotionListener {

   public AntiHunger() {
       super("AntiHunger", "GegenHunger", new Color(66, 135, 245), Categorys.PLAYER);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(18);
   }

   @Override
   public void onPreMotion(EventMotion event) {
      event.setOnGround(false);
   }
}
