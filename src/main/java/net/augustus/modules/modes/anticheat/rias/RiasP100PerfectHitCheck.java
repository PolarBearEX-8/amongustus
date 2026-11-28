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

public class RiasP100PerfectHitCheck extends ModuleMode {
   private int attacksThisTick;
   private EntityLivingBase targetEntity = null;
   private final StatisticalSampleBuffer hitTimingBuffer = new StatisticalSampleBuffer(25);

   @Override
   public void onTick() {
   }

   public RiasP100PerfectHitCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
   }

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C02PacketUseEntity) {
         C02PacketUseEntity packet2 = (C02PacketUseEntity)packet;
         if (packet2.getAction() == C02PacketUseEntity.Action.ATTACK) {
            Entity entityFromWorld = packet2.getEntityFromWorld(mc.theWorld);
            if (entityFromWorld instanceof EntityLivingBase) {
               this.targetEntity = (EntityLivingBase)entityFromWorld;
               this.attacksThisTick++;
            }
         }
      }
   }

   private void alertPerfectHit() {
      ChatUtil.sendChat("Rias P100: PerfectHit1!");
   }
}
