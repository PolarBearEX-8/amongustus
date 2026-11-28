package net.augustus.modules.modes.velocity;

import net.augustus.events.EventPacket;
import net.augustus.events.listeners.PacketListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.network.PacketFlushMarker;
import net.minecraft.network.Packet;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.minecraft.block.BlockAir;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.util.MathHelper;

public class IntavePolarFlagVelocityMode extends VelocityMode implements PacketListener, UpdateListener {
   private final BooleanValue polar;
   private final Queue queuedPackets;
   private boolean sinkingActive;
   private double hurtStartY;
   private int transactionCounter;

   public IntavePolarFlagVelocityMode(Velocity velocity, SettingGroup doubleValue) {
       super(velocity, doubleValue, "Intave/PolarFlag");
      this.polar = (BooleanValue)new BooleanValue(this.parentModule, "Polar", false).withVisibility(() -> this.isSelected());
      this.queuedPackets = new ConcurrentLinkedQueue();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(43);
   }

   @Override
   public void onEnable() {
      if (mc.thePlayer != null) {
         this.queuedPackets.forEach(obj -> {

            try {
               if (!(obj instanceof PacketFlushMarker)) {
                  ((Packet)obj).processPacket(mc.getNetHandler().getNetworkManager().getNetHandler());
               }
            } catch (Exception e) {
            }
         });
      }

      this.queuedPackets.clear();
   }

   @Override
   public void onDisable() {
      this.sinkingActive = false;
      this.queuedPackets.clear();
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new IntavePolarFlagVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (!this.canApplyVelocity()) {
         if (!this.queuedPackets.isEmpty()) {
            this.flushQueuedPackets();
         }

         this.sinkingActive = false;
      } else if (mc.thePlayer != null && mc.thePlayer.ticksExisted >= 100) {
         if (event.getPacket() instanceof S32PacketConfirmTransaction && !this.sinkingActive) {
            this.transactionCounter++;
            if (this.polar.getValue() ? this.transactionCounter > 2 : this.transactionCounter > 0) {
               this.transactionCounter = 0;
               this.flushQueuedPackets();
            }
         }

         if (event.getPacket() instanceof S19PacketEntityStatus) {
            S19PacketEntityStatus packet = (S19PacketEntityStatus)event.getPacket();
            if (packet.getEntity(mc.theWorld) == mc.thePlayer && packet.getOpCode() == 2 && mc.thePlayer.onGround) {
               if (!this.sinkingActive) {
                  this.hurtStartY = mc.thePlayer.posY;
               }

               this.sinkingActive = true;
            }
         }

         if (!(event.getPacket() instanceof S02PacketChat)
            && !(event.getPacket() instanceof S19PacketEntityStatus)
            && !(event.getPacket() instanceof S40PacketDisconnect)
            && !(event.getPacket() instanceof S0BPacketAnimation)) {
            this.queuedPackets.add(event.getPacket());
            event.setCancelled(true);
         }

         if (event.getPacket() instanceof S08PacketPlayerPosLook) {
            S08PacketPlayerPosLook packet2 = (S08PacketPlayerPosLook)event.getPacket();
            this.sinkingActive = false;
         }
      } else {
         this.queuedPackets.clear();
      }
   }

   @Override
   public void onPreUpdate() {
      if (this.canApplyVelocity() && mc.thePlayer != null) {
         if (!this.sinkingActive) {
            int n = MathHelper.floor_double(mc.thePlayer.getEntityBoundingBox().minY);
            int n2 = MathHelper.ceiling_double_int(mc.thePlayer.getEntityBoundingBox().minY);
            if (Math.abs((double)n - mc.thePlayer.getEntityBoundingBox().minY) < 1.0E-4) {
               mc.thePlayer.setPosition(mc.thePlayer.posX, (double)n, mc.thePlayer.posZ);
            } else if (Math.abs((double)n2 - mc.thePlayer.getEntityBoundingBox().minY) < 1.0E-4) {
               mc.thePlayer.setPosition(mc.thePlayer.posX, (double)n2, mc.thePlayer.posZ);
            }
         }
      }
   }

   @Override
   public void onPostUpdate() {
      if (this.canApplyVelocity() && mc.thePlayer != null && mc.theWorld != null) {
         if (this.sinkingActive) {
            mc.thePlayer.posY -= 1.0E-7;
         }

         if (this.hurtStartY - mc.thePlayer.posY > 0.03 || !(mc.theWorld.getBlockState(mc.thePlayer.getPosition()).getBlock() instanceof BlockAir)) {
            this.sinkingActive = false;
         }
      }
   }

   @Override
   public void onPacketSend(EventPacket event) {
      if (event.getPacket() instanceof C03PacketPlayer) {
         if (((C03PacketPlayer)event.getPacket()).isMoving()) {
            mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
         }
      }
   }

   private void flushQueuedPackets() {
      this.queuedPackets.forEach(obj -> {

         try {
            if (!(obj instanceof PacketFlushMarker)) {
               ((Packet)obj).processPacket(mc.getNetHandler().getNetworkManager().getNetHandler());
            }
         } catch (Exception e) {
         }
      });
      this.queuedPackets.clear();
   }

}
