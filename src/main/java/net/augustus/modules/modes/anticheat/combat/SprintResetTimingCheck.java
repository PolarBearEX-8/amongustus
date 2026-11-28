package net.augustus.modules.modes.anticheat.combat;

import net.augustus.modules.ModuleMode;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.NumberFormatUtil;
import net.augustus.utils.math.StatisticalSampleBuffer;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class SprintResetTimingCheck extends ModuleMode {
   private boolean didSprintReset;
   private final StatisticalSampleBuffer resetCountBuffer;
   private int ticksSinceAttack;
   private final StatisticalSampleBuffer startHurtTimeBuffer = new StatisticalSampleBuffer(20);
   private int ticksSinceHurt;
   private final StatisticalSampleBuffer stopSprintDurationBuffer;
   private final StatisticalSampleBuffer resetSuccessBuffer;
   private final StatisticalSampleBuffer endHurtTimeBuffer = new StatisticalSampleBuffer(20);
   private int sprintStartCount;
   private EntityLivingBase targetEntity;
   private int ticksSinceStopSprint;

   public void onClientPacket(Packet packet) {
   }

   private boolean hasActiveTarget() {
      return this.targetEntity != null && this.ticksSinceAttack <= 40;
   }

   public void handlePlayerPacket(C03PacketPlayer packet) {
      this.ticksSinceAttack++;
      if (this.ticksSinceStopSprint != -1) {
         this.ticksSinceStopSprint++;
      }

      if (this.hasActiveTarget()) {
         this.ticksSinceHurt++;
         if (this.targetEntity.hurtTime == 9) {
            this.ticksSinceHurt = 0;
            if (this.sprintStartCount > 0) {
               double sprintStartCount2 = (double)this.sprintStartCount;
               this.resetCountBuffer.add(sprintStartCount2);
               if (this.resetCountBuffer.isFull()) {
                  this.logMessage("Count AVG: " + NumberFormatUtil.formatDecimal(this.resetCountBuffer.mean(), 3) + " " + NumberFormatUtil.formatDecimal(this.resetCountBuffer.stdDev(), 3));
               }
            }

            this.sprintStartCount = 0;
            double d = this.didSprintReset ? 1.0 : 0.0;
            this.resetSuccessBuffer.add(d);
            this.didSprintReset = false;
         }
      }
   }

   public void handleEntityActionPacket(C0BPacketEntityAction packet) {
      if (!this.hasActiveTarget()) {
         this.ticksSinceStopSprint = -1;
         this.sprintStartCount = 0;
      } else {
         C0BPacketEntityAction.Action action = packet.getAction();
         if (action == C0BPacketEntityAction.Action.START_SPRINTING) {
            this.sprintStartCount++;
            double ticksSinceHurt2 = (double)this.ticksSinceHurt;
            this.startHurtTimeBuffer.add(ticksSinceHurt2);
            if (this.ticksSinceStopSprint != -1) {
               if (this.ticksSinceStopSprint == 0) {
                  this.logMessage("IMPOSSIBLE DURATION!");
               }

               double ticksSinceStopSprint2 = (double)this.ticksSinceStopSprint;
               this.stopSprintDurationBuffer.add(ticksSinceStopSprint2);
            }

            this.didSprintReset = true;
            if (this.isDebugOutputEnabled()) {
               if (this.startHurtTimeBuffer.isFull()) {
                  String str2 = "StartHurtTimes AVG: "
                     + NumberFormatUtil.formatDecimal(this.startHurtTimeBuffer.mean(), 3)
                     + " "
                     + NumberFormatUtil.formatDecimal(this.startHurtTimeBuffer.stdDev(), 3);
                  this.logMessage(str2);
               }

               if (this.endHurtTimeBuffer.isFull()) {
                  String str3 = "EndHurtTimes AVG: "
                     + NumberFormatUtil.formatDecimal(this.endHurtTimeBuffer.mean(), 3)
                     + " "
                     + NumberFormatUtil.formatDecimal(this.endHurtTimeBuffer.stdDev(), 3);
                  this.logMessage(str3);
               }

               this.logMessage("Success AVG: " + NumberFormatUtil.formatDecimal(this.resetSuccessBuffer.mean(), 3) + " " + NumberFormatUtil.formatDecimal(this.resetSuccessBuffer.stdDev(), 3));
               if (this.stopSprintDurationBuffer.isFull()) {
                  this.logMessage("Duration AVG: " + NumberFormatUtil.formatDecimal(this.stopSprintDurationBuffer.mean(), 3) + " " + NumberFormatUtil.formatDecimal(this.stopSprintDurationBuffer.stdDev(), 3));
               }
            }
         } else if (action == C0BPacketEntityAction.Action.STOP_SPRINTING) {
            double ticksSinceHurt3 = (double)this.ticksSinceHurt;
            this.endHurtTimeBuffer.add(ticksSinceHurt3);
            this.ticksSinceStopSprint = 0;
         }
      }
   }

   public SprintResetTimingCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.stopSprintDurationBuffer = new StatisticalSampleBuffer(20);
      this.resetCountBuffer = new StatisticalSampleBuffer(40);
      this.resetSuccessBuffer = new StatisticalSampleBuffer(200);
      this.ticksSinceStopSprint = -1;
      this.sprintStartCount = 0;
      this.ticksSinceHurt = 0;
      this.targetEntity = null;
      this.didSprintReset = false;
   }

   private boolean isDebugOutputEnabled() {
      return true;
   }

   public void handleAttackPacket(C02PacketUseEntity packet) {
      Entity entityFromWorld = packet.getEntityFromWorld(mc.theWorld);
      if (entityFromWorld instanceof EntityLivingBase) {
         this.targetEntity = (EntityLivingBase)entityFromWorld;
      }

      this.ticksSinceAttack = 0;
   }

   public void onTick() {
   }

   private void logMessage(String str) {
      ChatUtil.sendChat(str);
   }

}
