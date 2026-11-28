package net.augustus.modules.modes.fly;

import net.augustus.events.EventBlockBoundingBox;
import net.augustus.events.EventClick;
import net.augustus.events.EventMotion;
import net.augustus.events.EventMove;
import net.augustus.events.EventMoveEntity;
import net.augustus.events.EventMoveInput;
import net.augustus.events.EventPacket;
import net.augustus.events.EventPacketDelay;
import net.augustus.modules.movement.Fly;
import net.augustus.utils.interfaces.MC;

public abstract class FlyMode implements MC {
   private final String name;
   public final Fly fly;

   public FlyMode(String str, Fly fly2) {
      this.name = str;
      this.fly = fly2;
   }

   public abstract void onEnable();

   public abstract void onDisable();

   public abstract void onPreUpdate();

   public abstract void onMotion(EventMotion event);

   public abstract void onMoveEntity(EventMoveEntity event);

   public void onMoveInput(EventMoveInput event) {
   }

   public String getName() {
      return this.name;
   }

   public void onJumpMove(EventMove event) {
   }

   public void onMoveFlying(EventMove event) {
   }

   public void onBlockBoundingBox(EventBlockBoundingBox event) {
   }

   public void onReadPacket(EventPacket event) {
   }

   public void onSendPacket(EventPacket event) {
   }

   public void onPostUpdate() {
   }

   public void onOutgoingPacketDelay(EventPacketDelay event) {
   }

   public void onIncomingPacketDelay(EventPacketDelay event) {
   }

   public void onClick(EventClick event) {
   }

   public void onEarlyTick() {
   }

   public void onSilentSlot() {
   }




}
