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

public class RiasP104FirstHitCheck extends ModuleMode {
   private int attacksThisTick;
   private int ticksSinceFirstHit;
   private EntityLivingBase targetEntity;
   private final StatisticalSampleBuffer firstHitBuffer = new StatisticalSampleBuffer(25);
   private boolean inCombat;

   private void alertFirstHit(double d, double d2) {
      ChatUtil.sendChat("Rias P104: FirstHit: " + d + ", " + d2);
   }

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
               ++this.attacksThisTick;
            }
         }
      }
   }

   public RiasP104FirstHitCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
   }
}
