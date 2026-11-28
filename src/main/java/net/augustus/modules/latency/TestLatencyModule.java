package net.augustus.modules.latency;

import net.minecraft.util.MovingObjectPosition;
import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.EventClick;
import net.augustus.events.EventPacketDelay;
import net.augustus.events.listeners.PacketDelayListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class TestLatencyModule extends Module implements PacketDelayListener, TickListener, ClickListener {
   private EntityLivingBase targetEntity;
   private boolean delayingOutgoing;
   private int remainingTicks;
   private boolean pendingExtraClick;
   private boolean unusedFlag;
   private final TimeHelper timeHelper;

   public TestLatencyModule() {
       super("TestLatencyModule", "TestModul", Color.red, Categorys.LATENCY);
      this.delayingOutgoing = false;
      this.remainingTicks = 0;
      this.pendingExtraClick = false;
      this.unusedFlag = false;
      this.timeHelper = new TimeHelper();
   }

   public void onEnable() {
   }

   public void onDisable() {
   }

   public void updateEventSubscriptions() {
      this.toggleEventSubscription(25);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(7);
   }

   public void onOutgoingPacketDelay(EventPacketDelay event) {
      Packet packet = event.getPacket();
      if (this.delayingOutgoing) {
         event.setDelayed(true);
      }

      if (packet instanceof C02PacketUseEntity) {
         C02PacketUseEntity packet2 = (C02PacketUseEntity)packet;
         if (packet2.getAction() == C02PacketUseEntity.Action.ATTACK) {
            Entity entityFromWorld = packet2.getEntityFromWorld(mc.theWorld);
            if (entityFromWorld instanceof EntityLivingBase) {
               this.targetEntity = (EntityLivingBase)entityFromWorld;
               this.timeHelper.reset();
               if (this.targetEntity.hurtTime <= 1 && this.remainingTicks <= 0 && !event.isDelayed()) {
                  this.remainingTicks = 10;
                  this.pendingExtraClick = true;
               }
            }
         }
      }
   }

   public void onEarlyTick() {
      if (this.timeHelper.reached(2000L)) {
         this.targetEntity = null;
      }

      if (this.targetEntity != null) {
         this.remainingTicks--;
      }
   }

   public void onPostTick() {
      if (this.targetEntity != null) {
         if (this.remainingTicks <= 0 && (this.delayingOutgoing || this.pendingExtraClick)) {
            this.pendingExtraClick = false;
            this.delayingOutgoing = false;
            this.unusedFlag = false;
         }
      }
   }

   public void onClick(EventClick event) {
      if (this.targetEntity != null && this.remainingTicks > 0) {
         if (mc.objectMouseOver == null || mc.objectMouseOver.typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY || !mc.objectMouseOver.entityHit.equals(this.targetEntity)) {
            this.delayingOutgoing = true;
         } else if (this.pendingExtraClick && !this.delayingOutgoing) {
            event.addLeftClicks((byte)1);
            event.setBreakingBlock(true);
            event.setCriticalHit(true);
            this.delayingOutgoing = true;
         }
      }
   }

}
