package net.augustus.modules.modes.anticheat.autoclicker;

import net.minecraft.util.MovingObjectPosition;
import java.util.ArrayList;
import net.augustus.modules.ModuleMode;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.utils.TimeHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AutoClickerP3Check extends ModuleMode {
   private int attacksThisTick;
   private final TimeHelper timeHelper;
   private EntityLivingBase currentTarget;
   private final ArrayList zeroHurtTimeAttackSamples;
   private double violationLevel = 0.0;

   public AutoClickerP3Check(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.zeroHurtTimeAttackSamples = new ArrayList();
      this.currentTarget = null;
      this.timeHelper = new TimeHelper();
      this.visibleInGui = true;
   }

   public void onTick() {
   }

   public void resetState() {
      this.zeroHurtTimeAttackSamples.clear();
      this.violationLevel = 0.0;
   }

   public void onClientPacket(Packet packet) {
      if (packet instanceof C03PacketPlayer) {
         boolean flag = mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityLivingBase;
         if (this.timeHelper.reached(3000L)) {
            this.currentTarget = null;
         }

         if (flag && this.attacksThisTick > 0) {
            this.timeHelper.reset();
            this.currentTarget = (EntityLivingBase)mc.objectMouseOver.entityHit;
         }

         if (flag && ((EntityLivingBase)mc.objectMouseOver.entityHit).hurtTime == 0 && ((EntityLivingBase)mc.objectMouseOver.entityHit).prevHurtTime == 0) {
            if (this.currentTarget == null) {
               return;
            }

            this.zeroHurtTimeAttackSamples.add(this.attacksThisTick);

            while (this.zeroHurtTimeAttackSamples.size() > 20) {
               this.zeroHurtTimeAttackSamples.remove(0);
            }

            if (this.zeroHurtTimeAttackSamples.size() == 20) {
               double d = MathStatisticsUtil.averageInt(this.zeroHurtTimeAttackSamples);
               double d2 = MathStatisticsUtil.stdDevInt(this.zeroHurtTimeAttackSamples);
               int n = MathStatisticsUtil.minInt(this.zeroHurtTimeAttackSamples);
               int n2 = MathStatisticsUtil.maxInt(this.zeroHurtTimeAttackSamples);
               if (d > 0.1 && d2 < 0.25 || n > 0 || n2 < 2 && d > 0.88) {
                  this.violationLevel++;
                  if (d2 == 0.0) {
                     this.violationLevel++;
                  }

                  if (n > 0) {
                     this.violationLevel += 2.0;
                  }

                  ChatUtil.sendChat("AutoClickerP3 vl: " + this.violationLevel + " " + d2);
                  this.zeroHurtTimeAttackSamples.clear();
               } else {
                  this.violationLevel = Math.max(this.violationLevel - 0.001, 0.0);
               }

               if (this.violationLevel > 10.0) {
                  this.violationLevel = 0.0;
                  ChatUtil.sendChat("Banned I think! Reset Violations");
               }
            }
         }

         this.attacksThisTick = 0;
      }

      if (packet instanceof C02PacketUseEntity) {
         if (((C02PacketUseEntity)packet).getAction() == C02PacketUseEntity.Action.ATTACK) {
            this.attacksThisTick++;
         }
      }
   }


}
