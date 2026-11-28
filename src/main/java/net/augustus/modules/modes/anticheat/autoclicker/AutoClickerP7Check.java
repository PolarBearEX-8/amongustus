package net.augustus.modules.modes.anticheat.autoclicker;

import java.util.ArrayList;
import net.augustus.modules.ModuleMode;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.utils.TimeHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;

public class AutoClickerP7Check extends ModuleMode {
   private final TimeHelper timeHelper;
   private final ArrayList attackSamples;
   private int attacksThisTick;
   private double violationLevel = 0.0;

   public void onTick() {
   }

   public void resetState() {
      this.attackSamples.clear();
      this.violationLevel = 0.0;
   }

   public AutoClickerP7Check(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.attackSamples = new ArrayList();
      this.timeHelper = new TimeHelper();
      this.visibleInGui = true;
   }

   public void onClientPacket(Packet packet) {
      if (packet instanceof C03PacketPlayer) {
         if (this.isAimingAtLivingEntity()) {
            this.attackSamples.add(this.attacksThisTick);

            while (this.attackSamples.size() > 20) {
               this.attackSamples.remove(0);
            }

            if (this.attackSamples.size() == 20) {
               double d = MathStatisticsUtil.averageInt(this.attackSamples);
               double d2 = MathStatisticsUtil.stdDevInt(this.attackSamples);
               int n = MathStatisticsUtil.minInt(this.attackSamples);
               int n2 = MathStatisticsUtil.maxInt(this.attackSamples);
               if (d > 0.1 && d2 < 0.35 || n > 0 || n2 < 2 && d > 0.7) {
                  this.violationLevel++;
                  if (d2 == 0.0) {
                     this.violationLevel++;
                  }

                  if (n > 0) {
                     this.violationLevel += 2.0;
                  }

                  ChatUtil.sendChat("AutoClickerP7 vl: " + this.violationLevel + " " + d2);
                  this.attackSamples.clear();
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
