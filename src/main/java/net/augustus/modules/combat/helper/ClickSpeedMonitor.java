package net.augustus.modules.combat.helper;

import net.minecraft.util.MovingObjectPosition;
import net.augustus.events.EventPacket;
import net.augustus.utils.clicker.SwingDelayPair;
import com.google.common.util.concurrent.AtomicDouble;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.augustus.utils.TimeHelper;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.server.S07PacketRespawn;

public class ClickSpeedMonitor implements MC {
   private int previousSwingDelay;
   public int zeroDelaySwingCount;
   private final boolean strictCheck;
   private boolean exceedsCpsLimit;
   private int ticksSinceLastSwing;
   private boolean placedBlockThisTick;
   private final ArrayList recentSwingDelays;
   private final ArrayList delayHistory;
   private final ArrayList pendingSwingPairs;
   private boolean digging;
   private int ticksSinceAttack;
   private final boolean monitoringEnabled = true;
   private int sampleWindowSize;

   private double calculateAverageDelay(List list) {
      ArrayList list2 = new ArrayList(list);
      if (!list2.isEmpty()) {
         list2.remove(0);
         list2.add(1);
         return list2.stream().mapToDouble(obj -> ((Number)obj).doubleValue()).average().orElse(0.0);
      } else {
         return 0.0;
      }
   }

   private static void accumulateSquaredDeviation(AtomicDouble atomicDouble, double d, Integer num) {
      atomicDouble.getAndAdd(Math.pow(num.doubleValue() - d, 2.0));
   }

   private boolean isPointingAtLivingEntity() {
      return mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityLivingBase;
   }

   public ClickSpeedMonitor(int n) {
      this.strictCheck = false;
      this.pendingSwingPairs = new ArrayList();
      this.ticksSinceAttack = 0;
      this.zeroDelaySwingCount = 0;
      this.delayHistory = new ArrayList();
      this.recentSwingDelays = new ArrayList();
      this.ticksSinceLastSwing = 0;
      this.placedBlockThisTick = false;
      this.digging = false;
      this.exceedsCpsLimit = false;
      this.sampleWindowSize = n;
   }

   public void onPacketReceive(EventPacket event) {
      if (event.getPacket() instanceof S07PacketRespawn) {
         this.zeroDelaySwingCount = 0;
      }
   }

   private double calculateCps(List list) {
      return 20.0 / this.calculateAverageDelay(list);
   }

   public void onPacketSend(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof C03PacketPlayer) {
         this.exceedsCpsLimit = false;

         for (SwingDelayPair swingDelayPair : (Iterable<SwingDelayPair>)(this.pendingSwingPairs)) {
            if (this.ticksSinceAttack <= 0 || this.isPointingAtLivingEntity()) {
               if (swingDelayPair.currentDelay == 0) {
                  this.zeroDelaySwingCount++;
               }

               this.recentSwingDelays.add(swingDelayPair.currentDelay);

               while (this.recentSwingDelays.size() > this.sampleWindowSize) {
                  this.recentSwingDelays.remove(0);
               }

               this.exceedsCpsLimit = this.calculateCps(this.recentSwingDelays) > 14.75;
            }
         }

         this.pendingSwingPairs.clear();
         this.ticksSinceLastSwing++;
         this.ticksSinceAttack++;
         this.placedBlockThisTick = false;
      }

      if (packet instanceof C0APacketAnimation && !this.digging && !this.placedBlockThisTick) {
         boolean flag2 = this.ticksSinceLastSwing > 0;
         if (this.ticksSinceLastSwing <= 15 && this.previousSwingDelay <= 15 && true && flag2) {
            SwingDelayPair swingDelayPair2 = new SwingDelayPair(this.ticksSinceLastSwing, this.previousSwingDelay);
            this.pendingSwingPairs.add(swingDelayPair2);
         }

         this.previousSwingDelay = this.ticksSinceLastSwing;
         this.ticksSinceLastSwing = 0;
      }

      if (packet instanceof C02PacketUseEntity) {
         if (((C02PacketUseEntity)packet).getAction() == C02PacketUseEntity.Action.ATTACK) {
            this.ticksSinceAttack = 0;
         }
      }

      if (packet instanceof C08PacketPlayerBlockPlacement) {
         this.placedBlockThisTick = true;
      }

      if (packet instanceof C07PacketPlayerDigging) {
         C07PacketPlayerDigging packet3 = (C07PacketPlayerDigging)packet;
         if (packet3.getStatus() == net.minecraft.network.play.client.C07PacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            this.digging = true;
            if (mc.playerController.isInCreativeMode()) {
               this.digging = false;
            }
         }

         if (packet3.getStatus() == net.minecraft.network.play.client.C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK
            || packet3.getStatus() == net.minecraft.network.play.client.C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
            this.digging = false;
         }
      }
   }

   private double calculateStandardDeviation(Collection items) {
      double d = 0.0;
      double d2 = 0.0;

      for (Integer num : (Iterable<Integer>)(items)) {
         d += num.doubleValue();
      }

      double d3 = d / (double)items.size();

      for (Integer num2 : (Iterable<Integer>)(items)) {
         d2 += (num2.doubleValue() - d3) * (num2.doubleValue() - d3);
      }

      return Math.sqrt(d2 / (double)items.size());
   }

   public boolean isExceedsCpsLimit() {
      return this.exceedsCpsLimit;
   }
}
