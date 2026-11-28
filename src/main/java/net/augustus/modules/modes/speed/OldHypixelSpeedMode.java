package net.augustus.modules.modes.speed;

import net.augustus.events.EventMotion;
import net.augustus.events.EventMove;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.modules.movement.Speed;
import net.minecraft.potion.Potion;

public class OldHypixelSpeedMode extends SpeedMode {
   public OldHypixelSpeedMode(String str, Speed speed2) {
      super(str, speed2);
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
   public void onPostUpdate() {
   }

   @Override
   public void onMotion(EventMotion event) {
   }

   @Override
   public void onMoveEntity(EventMoveEntity event) {
      if (mc.thePlayer.getActivePotionEffect(Potion.moveSpeed) != null) {
         double d = 0.01;
         double d2 = 0.009;
         double[] doubles = MoveUtil.calculateForwardMotion((double)DefaultRandomGenerator.instance.nextFloat(d2, d), MoveUtil.getDirectionDegrees(false));
         event.setX(event.getX() + doubles[0]);
         event.setZ(event.getZ() + doubles[1]);
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (MoveUtil.isMoving()) {
         event.setJump(true);
      }
   }

   @Override
   public void onJumpMove(EventMove event) {
      super.onJumpMove(event);
      if (MoveUtil.isMoving() && mc.thePlayer.getActivePotionEffect(Potion.moveSpeed) != null) {
         double speed2 = MoveUtil.getSpeed();
         double d = 0.1;
         double d2 = 0.09;
         MoveUtil.setSpeed((float)(speed2 + (double)DefaultRandomGenerator.instance.nextFloat(d2, d)), false);
      }
   }
}
