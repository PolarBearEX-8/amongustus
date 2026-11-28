package net.augustus.modules.modes.speed;

import net.augustus.events.EventAIMoveSpeed;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMove;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.EventTimer;
import net.augustus.modules.movement.Speed;
import net.augustus.utils.interfaces.MC;

public abstract class SpeedMode implements MC {
   private final String name;
   public final Speed speed;

   public SpeedMode(String str, Speed speed2) {
      this.name = str;
      this.speed = speed2;
   }

   public abstract void onEnable();

   public abstract void onDisable();

   public abstract void onPreUpdate();

   public abstract void onPostUpdate();

   public abstract void onMotion(EventMotion event);

   public abstract void onMoveEntity(EventMoveEntity event);

   public abstract void onPreMoveInput(EventMoveInput event);

   public String getName() {
      return this.name;
   }

   public boolean isMoving() {
      return mc.thePlayer.moveForward != 0.0F || mc.thePlayer.moveStrafing != 0.0F && !mc.thePlayer.isCollidedHorizontally;
   }

   public boolean isMovingOnGround() {
      return this.isMoving() && mc.thePlayer.onGround;
   }

   public void onJumpMove(EventMove event) {
   }

   public void onMoveFlying(EventMove event) {
   }

   public void onEarlyTick() {
   }

   public void onSendPacket(EventPacket event) {
   }

   public void onReadPacket(EventPacket event) {
   }

   public void onTimer(EventTimer event) {
   }

   public void onAIMoveSpeed(EventAIMoveSpeed event) {
   }

   public void onOutgoingPacketDelay(EventPacketDelay event) {
   }

   public void onPostMoveInput(EventMoveInput event) {
   }



}
