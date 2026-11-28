package net.augustus.modules.modes.anticheat.rias;

import java.util.LinkedList;
import net.augustus.modules.ModuleMode;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.StatisticalSampleBuffer;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class RiasP103HurtTimeHitCheck extends ModuleMode {
   private boolean attackedThisTick;
   private double violationLevel;
   private final StatisticalSampleBuffer[] hurtTimeBuffers;
   private final LinkedList recentHurtTimes;
   private EntityLivingBase targetEntity;

   private double computeHurtTimeScore() {
      return 0.0;
   }

   private void alertHurtTimeHit(String str) {
      ChatUtil.sendChat("Rias P103: HurtTimeHit: " + str);
   }

   private void evaluateHurtTimeBuffers() {
   }

   private boolean areAllBuffersReady() {

      for (StatisticalSampleBuffer statisticalSampleBuffer : this.hurtTimeBuffers) {
         if (!statisticalSampleBuffer.isFull()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C02PacketUseEntity) {
         C02PacketUseEntity packet2 = (C02PacketUseEntity)packet;
         if (packet2.getAction() == C02PacketUseEntity.Action.ATTACK) {
            Entity entityFromWorld = packet2.getEntityFromWorld(mc.theWorld);
            if (entityFromWorld instanceof EntityLivingBase) {
               this.targetEntity = (EntityLivingBase)entityFromWorld;
               this.attackedThisTick = true;
            }
         }
      }
   }

   @Override
   public void onTick() {
   }

   private void clearAllBuffers() {
      for (StatisticalSampleBuffer statisticalSampleBuffer : this.hurtTimeBuffers) {
         statisticalSampleBuffer.clear();
      }
   }

   public RiasP103HurtTimeHitCheck(long l, String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.hurtTimeBuffers = new StatisticalSampleBuffer[10];
      this.recentHurtTimes = new LinkedList();

      for (int i = 0; i < this.hurtTimeBuffers.length; i++) {
         this.hurtTimeBuffers[i] = new StatisticalSampleBuffer(20);
      }
   }

   private static double unboxDouble(Double d) {
      return d.doubleValue();
   }
}
