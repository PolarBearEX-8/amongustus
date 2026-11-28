package net.augustus.modules.modes.anticheat.rias;

import net.augustus.modules.ModuleMode;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.StatisticalSampleBuffer;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.viaversion.packet.C0APacketSwingHand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C0APacketAnimation;

public class RiasP105FirstHitCpsCheck extends ModuleMode {
   private double violationLevel;
   private int preHitSwingCount;
   private boolean attackedThisTick;
   private boolean swungThisTick;
   private final StatisticalSampleBuffer combatCpsBuffer;
   private final StatisticalSampleBuffer firstHitCpsBuffer = new StatisticalSampleBuffer(20);
   private int combatTicks;
   private boolean inCombat;
   private int ticksSinceLastHit;
   private EntityLivingBase targetEntity;
   private static final String c = "Rias P105: FirstHitCps!";

   private void alertFirstHitCps() {
      ChatUtil.sendChat(c);
   }

   public RiasP105FirstHitCpsCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.combatCpsBuffer = new StatisticalSampleBuffer(40);
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
      if (packet instanceof C0APacketAnimation || packet instanceof C0APacketSwingHand) {
         this.swungThisTick = true;
      }
   }

   @Override
   public void onTick() {
   }
}
