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

public class RiasP101TriggerCheck extends ModuleMode {
   private final StatisticalSampleBuffer triggerDelayBuffer = new StatisticalSampleBuffer(10);
   private EntityLivingBase targetEntity = null;
   private int attacksThisTick;
   private boolean wasLookingAtTarget;
   private int swingsThisTick;
   private boolean isInCombat;
   private int ticksOnTarget;
   private static final String c = "Rias P101: Trigger!";

   @Override
   public void onTick() {
   }

   @Override
   public void onClientPacket(Packet packet) {
      if (packet instanceof C0APacketAnimation || packet instanceof C0APacketSwingHand) {
         ++this.swingsThisTick;
      }
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

   public RiasP101TriggerCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
   }

   private void alertTrigger() {
      ChatUtil.sendChat(c);
   }

}
