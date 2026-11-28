package net.augustus.modules.modes.anticheat.autoclicker;

import java.util.ArrayList;
import net.augustus.modules.ModuleMode;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.NumberFormatUtil;
import net.augustus.utils.math.MathStatisticsUtil;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;

public class AutoClickerP6Check extends ModuleMode {
   private final ArrayList stdDevHistory;
   private double violationLevel;
   private int missedSwingsThisTick;
   private int idleTickCount;
   private final ArrayList tickSwingSamples;
   private final int sampleWindowSize = 20;
   private final ArrayList meanHistory;

   public void onClientPacket(Packet packet) {
      if (packet instanceof C0APacketAnimation && !mc.playerController.isHittingBlock) {
         this.missedSwingsThisTick++;
      }

      if (packet instanceof C02PacketUseEntity && ((C02PacketUseEntity)packet).getAction() == C02PacketUseEntity.Action.ATTACK) {
         this.missedSwingsThisTick = Math.max(0, this.missedSwingsThisTick - 1);
      }

      if (packet instanceof C03PacketPlayer) {
         this.idleTickCount = this.missedSwingsThisTick == 0 ? this.idleTickCount + 1 : 0;
         if (this.idleTickCount > 4) {
            return;
         }

         this.tickSwingSamples.add(this.missedSwingsThisTick);

         while (this.tickSwingSamples.size() > 20) {
            this.tickSwingSamples.remove(0);
         }

         this.missedSwingsThisTick = 0;
         if (this.tickSwingSamples.size() == 20) {
            double d = MathStatisticsUtil.averageInt(this.tickSwingSamples);
            double d2 = MathStatisticsUtil.stdDevInt(this.tickSwingSamples);
            this.meanHistory.add(d);
            this.stdDevHistory.add(d2);
            this.tickSwingSamples.clear();
         }

         if (this.meanHistory.size() >= 7) {
            double d3 = MathStatisticsUtil.stdDevDouble(this.meanHistory);
            if (d3 < 0.008) {
               ChatUtil.sendChat("AutoClickerP6 vl: " + this.violationLevel + " " + NumberFormatUtil.formatDecimal(d3, 3));
               this.violationLevel++;
            } else {
               this.violationLevel = Math.max(this.violationLevel - 0.05, 0.0);
            }

            this.meanHistory.clear();
         }

         if (this.stdDevHistory.size() >= 7) {
            double d4 = MathStatisticsUtil.stdDevDouble(this.stdDevHistory);
            if (d4 < 0.008) {
               ChatUtil.sendChat("AutoClickerP6 vl: " + this.violationLevel + " " + NumberFormatUtil.formatDecimal(d4, 3));
               this.violationLevel++;
            } else {
               this.violationLevel = Math.max(this.violationLevel - 0.05, 0.0);
            }

            this.stdDevHistory.clear();
         }
      }
   }

   public void onTick() {
   }

   public AutoClickerP6Check(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.missedSwingsThisTick = 0;
      this.violationLevel = 0.0;
      this.idleTickCount = 0;
      this.tickSwingSamples = new ArrayList();
      this.meanHistory = new ArrayList();
      this.stdDevHistory = new ArrayList();
      this.visibleInGui = true;
   }

   public void resetState() {
      this.violationLevel = 0.0;
      this.tickSwingSamples.clear();
      this.meanHistory.clear();
   }


}
