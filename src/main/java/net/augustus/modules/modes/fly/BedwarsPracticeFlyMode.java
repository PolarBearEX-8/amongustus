package net.augustus.modules.modes.fly;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMoveEntity;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Fly;

public class BedwarsPracticeFlyMode extends FlyMode {
   public BedwarsPracticeFlyMode(String str, Fly fly2) {
      super(str, fly2);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate() {
   }

   @Override
   public void onMotion(EventMotion event) {
      mc.thePlayer.motionY = 0.0;
      MoveUtil.setSpeed(0.3F);
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
   }
}
