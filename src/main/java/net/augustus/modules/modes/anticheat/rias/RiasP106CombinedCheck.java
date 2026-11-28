package net.augustus.modules.modes.anticheat.rias;

import net.augustus.modules.ModuleMode;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.StatisticalSampleBuffer;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;

public class RiasP106CombinedCheck extends ModuleMode {
   private int ticksInRange;
   private boolean attackedThisTick;
   private final StatisticalSampleBuffer hitDelayBuffer;
   private final StatisticalSampleBuffer firstHitBuffer;
   private int ticksSinceAttack;
   private EntityLivingBase targetEntity;
   private boolean wasInRange;
   private final StatisticalSampleBuffer combinedScoreBuffer;

   @Override
   public void onTick() {
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

   private void alertCombined(double d, double d2) {
      ChatUtil.sendChat("Rias P106: Combined: " + d + ", " + d2);
   }

   public RiasP106CombinedCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.firstHitBuffer = new StatisticalSampleBuffer(20);
      this.hitDelayBuffer = new StatisticalSampleBuffer(20);
      this.combinedScoreBuffer = new StatisticalSampleBuffer(20);
   }
}
