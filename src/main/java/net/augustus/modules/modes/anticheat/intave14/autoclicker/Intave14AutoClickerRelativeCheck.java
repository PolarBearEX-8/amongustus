package net.augustus.modules.modes.anticheat.intave14.autoclicker;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import net.augustus.modules.ModuleMode;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.network.Packet;

public class Intave14AutoClickerRelativeCheck extends ModuleMode {
   private boolean isDigging;
   private double violationLevel;
   private long lastInteractTime;
   private long lastSwingTime = 0L;
   private double lastKurtosis;
   private final Deque clickDelayDeque = new ArrayDeque();

   @Override
   public void onClientPacket(Packet packet) {
   }

   @Override
   public void onTick() {
   }

   private boolean shouldIgnoreClick(long l) {
      return l > 4000L
         || this.isDigging
         || System.currentTimeMillis() - this.lastInteractTime < 3000L
            && mc.thePlayer.getCurrentEquippedItem() != null
            && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemFishingRod;
   }

   private double calculateKurtosis(Collection items) {
      double d = 0.0;
      int n = 0;

      for (Object obj : items) {
         d += ((Long)obj).doubleValue();
         n++;
      }

      if (n < 3) {
         return 0.0;
      } else {
         double d2 = (double)n * (double)(n + 1) / ((double)(n - 1) * (double)(n - 2) * (double)(n - 3));
         double d3 = 3.0 * Math.pow((double)(n - 1), 2.0) / ((double)(n - 2) * (double)(n - 3));
         double d4 = d / (double)n;
         double d5 = 0.0;
         double d6 = 0.0;

         for (Object obj2 : items) {
            Number number = (Number)obj2;
            d5 += Math.pow(d4 - number.doubleValue(), 2.0);
            d6 += Math.pow(d4 - number.doubleValue(), 4.0);
         }

         return d2 * (d6 / Math.pow(d5 / d, 2.0)) - d3;
      }
   }

   public Intave14AutoClickerRelativeCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.violationLevel = 0.0;
      this.lastKurtosis = 0.0;
      this.isDigging = false;
      this.lastInteractTime = 0L;
   }
}
