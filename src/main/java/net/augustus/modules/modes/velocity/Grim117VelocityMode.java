package net.augustus.modules.modes.velocity;

import net.minecraft.network.play.client.C03PacketPlayer;
import net.augustus.events.EventMotion;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.MotionListener;
import net.augustus.events.listeners.PacketListener;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.SettingGroup;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Grim117VelocityMode extends VelocityMode implements PacketListener, MotionListener {
   private int lastSetbackTick = 0;
   private int cancelStage = 1;

   public Grim117VelocityMode(Velocity velocity, SettingGroup doubleValue) {
      super(velocity, doubleValue, "Grim-1.17");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(18);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new Grim117VelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (this.canApplyVelocity()) {
         Packet packet = event.getPacket();
         if (packet instanceof S12PacketEntityVelocity) {
            if (((S12PacketEntityVelocity)packet).getEntityID() == mc.thePlayer.getEntityId() && this.lastSetbackTick != mc.thePlayer.ticksExisted) {
               event.cancel();
               this.cancelStage = 1;
            }
         } else if (packet instanceof S08PacketPlayerPosLook) {
            this.lastSetbackTick = mc.thePlayer.ticksExisted;
         }
      }
   }

   @Override
   public void onPreMotion(EventMotion event) {
      if (this.cancelStage > 0 && this.canApplyVelocity()) {
         this.cancelStage++;
         if (this.cancelStage == 2) {
            mc.thePlayer
               .sendQueue
               .addToSendQueueUnchecked(
                  new C03PacketPlayer.C06PacketPlayerPosLook(
                     mc.thePlayer.prevPosX, mc.thePlayer.prevPosY, mc.thePlayer.prevPosZ, mc.thePlayer.prevRotationYaw, mc.thePlayer.prevRotationPitch, mc.thePlayer.prevPosY % 0.015625 == 0.0
                  )
               );
            mc.thePlayer.sendQueue.addToSendQueueUnchecked(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, new BlockPos(mc.thePlayer), EnumFacing.UP));
         } else {
            this.cancelStage = 0;
         }
      }
   }

}
