package net.augustus.modules.modes.anticheat.intave14.inventory;

import java.util.ArrayList;
import java.util.List;
import net.augustus.modules.ModuleMode;
import net.augustus.events.EventPacket;
import net.augustus.modules.modes.anticheat.AntiCheatCheckTracker;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.NumberFormatUtil;
import net.augustus.modules.modes.anticheat.AntiCheatCategory;
import net.augustus.Augustus;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Intave14InventorySlotCheck extends ModuleMode {
   private int lastWindowId;
   private long lastPacketTime;
   private long lastOpenTime;
   private ArrayList packetIntervals;
   private long lastClickNanoTime;
   private final boolean debugEnabled;
   private int lastSlotId;
   private final AntiCheatCheckTracker antiCheatCheckTracker;
   private List clickIntervals = new ArrayList();

   public void recordPacketInterval() {
      long currentTimeMs = System.currentTimeMillis();
      if (this.lastPacketTime != 0L) {
         this.packetIntervals.add(currentTimeMs - this.lastPacketTime);
         if (this.packetIntervals.size() >= 4) {
            this.packetIntervals.remove(0);
         }
      }

      this.lastPacketTime = currentTimeMs;
   }

   @Override
   public void onTick() {
   }

   private double computeSlotDistance(int n, int n2) {
      int[] ints = this.slotToGridCoords(n);
      int[] ints2 = this.slotToGridCoords(n2);
      return Math.sqrt((double)((ints[0] - ints2[0]) * (ints[0] - ints2[0]) + (ints[1] - ints2[1]) * (ints[1] - ints2[1])));
   }

   private void analyzeSlotTiming(int n, double d) {
   }

   private void onSlotClicked(int n) {
      double d = (double)(System.nanoTime() - this.lastClickNanoTime) / 1.0E9;
      if (d < 2.0) {
         this.clickIntervals.add(d);
      }

      if (this.clickIntervals.size() >= 11) {
         this.checkClickDeviation();
         this.clickIntervals.clear();
      }

      this.analyzeSlotTiming(n, d);
   }

   @Override
   public void onPacketEvent(EventPacket event) {
      if (event.getPacket() instanceof C03PacketPlayer) {
         this.recordPacketInterval();
      }
   }

   public Intave14InventorySlotCheck(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      super(str, antiCheatPlayerData2);
      this.packetIntervals = new ArrayList();
      this.lastPacketTime = 0L;
      this.antiCheatCheckTracker = new AntiCheatCheckTracker(1212, "", AntiCheatCategory.KILLAURA);
      this.debugEnabled = false;
   }

   private void checkClickDeviation() {
      double d = this.calculateStdDev(this.clickIntervals);
      double d2 = this.calculateAverageLong(this.packetIntervals);
      double d3 = d * 100.0;
      if (d3 < 2.0 && Math.abs(d2 - 50.0) < 40.0) {
         ChatUtil.sendChat(
            "Is clicking with regular deviation on items " + NumberFormatUtil.formatDecimal(d3, 2) + " deviation"
         );
      }
   }

   private int[] slotToGridCoords(int n) {
      return new int[]{n / 9 + 1, n % 9};
   }

   private void updateLastClick(int n, int n2) {
      this.lastSlotId = n;
      this.lastClickNanoTime = System.nanoTime();
      this.lastWindowId = n2;
   }

   private double calculateAverageLong(List list) {
      double d = 0.0;

      for (Object obj : list) {
         d += ((Long)obj).doubleValue();
      }

      return d == 0.0 ? 0.0 : d / (double)list.size();
   }

   @Override
   public void onClientPacket(Packet packet) {
   }

   private double calculateStdDev(List list) {
      double d = 0.0;

      for (Object obj : list) {
         d += ((Double)obj).doubleValue();
      }

      d /= (double)list.size();
      double d2 = 0.0;

      for (Object obj2 : list) {
         double d3 = ((Double)obj2).doubleValue();
         d2 += (d3 - d) * (d3 - d);
      }

      return Math.sqrt(d2 / (double)list.size());
   }
}
