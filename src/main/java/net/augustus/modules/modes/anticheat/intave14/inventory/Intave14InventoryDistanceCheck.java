package net.augustus.modules.modes.anticheat.intave14.inventory;

import net.augustus.modules.ModuleMode;
import net.augustus.events.EventPacket;
import net.augustus.utils.math.DataPoint2D;
import net.augustus.utils.ChatUtil;
import net.augustus.utils.math.Vec2d;
import net.augustus.utils.math.DoubleSampleWindow;
import net.augustus.utils.BoundedHistoryList;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import net.minecraft.network.play.server.S2DPacketOpenWindow;

public class Intave14InventoryDistanceCheck extends ModuleMode {
   private Vec2d lastSlotPosition;
   private long lastClickNanoTime;
   private BoundedHistoryList clickHistory;
   private boolean firstClickInWindow;
   private DoubleSampleWindow distanceSamples;
   private DoubleSampleWindow timeDiffSamples;

   public void onServerPacket(EventPacket event) {
      if (event.getPacket() instanceof S2DPacketOpenWindow) {
         this.firstClickInWindow = true;
      }
   }

   public void onClientPacket(Packet packet) {
      if (packet instanceof C0EPacketClickWindow) {
         C0EPacketClickWindow packet2 = (C0EPacketClickWindow)packet;
         Vec2d vec2d = this.slotToGridPosition(packet2.getSlotId());
         if (packet2.getClickedItem() == null) {
            return;
         }

         if (!this.firstClickInWindow) {
            double d = (double)(System.nanoTime() - this.lastClickNanoTime) / 1.0E9;
            System.out.println("TimeDiff: " + d);
            double length = vec2d.subtract(this.lastSlotPosition).length();
            this.clickHistory.add(new DataPoint2D(d, length));
            this.distanceSamples.add(length);
            this.timeDiffSamples.add(d);
            if (this.distanceSamples.isFull() && this.timeDiffSamples.isFull()) {
               double d2 = this.calculateVariance(this.distanceSamples);
               double d3 = this.calculateVariance(this.timeDiffSamples);
               if (d3 > 0.001) {
                  ChatUtil.sendChat("TimeVariance: " + d3);
                  System.out.println(d3 + " " + d2);
                  if (d3 < 0.02 && d2 > 2.0) {
                     short s = 16745;
                     String str = String.format("seems indifferent to distance taking items (variance mismatch: %.2f d, %.2f t)", d2, d3);
                     ChatUtil.sendChat("INTAVE ALERT: " + str);
                     this.distanceSamples.clear();
                     this.timeDiffSamples.clear();
                  }
               }
            }
         }

         this.firstClickInWindow = false;
         this.lastSlotPosition = vec2d;
         this.lastClickNanoTime = System.nanoTime();
      }
   }

   public Intave14InventoryDistanceCheck(String str, AntiCheatPlayerData antiCheatPlayerData2, long l) {
      super(str, antiCheatPlayerData2);
      this.firstClickInWindow = true;
      this.clickHistory = new BoundedHistoryList(100);
      this.distanceSamples = new DoubleSampleWindow(20);
      this.timeDiffSamples = new DoubleSampleWindow(20);
   }

   private double calculateVariance(DoubleSampleWindow doubleSampleWindow) {
      double d = 0.0;

      for (double d2 : (Iterable<Double>)(doubleSampleWindow.getSamples())) {
         d += d2;
      }

      double d3 = d / (double)doubleSampleWindow.getSamples().size();
      double d4 = 0.0;

      for (double d5 : (Iterable<Double>)(doubleSampleWindow.getSamples())) {
         d4 += Math.pow(d5 - d3, 2.0);
      }

      return d4 / (double)doubleSampleWindow.getSamples().size();
   }

   public void onTick() {
   }

   private Vec2d slotToGridPosition(int n) {
      int n2 = n / 9 + 1;
      int n3 = n - (n2 - 1) * 9;
      return new Vec2d((double)n2, (double)n3);
   }

   public void resetState() {
      this.distanceSamples.clear();
      this.timeDiffSamples.clear();
   }





}
