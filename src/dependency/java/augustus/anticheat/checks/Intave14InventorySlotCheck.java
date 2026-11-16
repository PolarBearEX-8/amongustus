package augustus.anticheat.checks;

import client.Client;
import client.setting.DirectOptionType;
import client.util.CompositeDoubleEntry;
import client.util.DecimalFormatter;
import client.util.Packet;
import client.util.RayTraceUtil;
import java.util.ArrayList;
import java.util.List;

public class Intave14InventorySlotCheck extends AntiCheatCheck {
   public long timestamp2;
   public long timestamp;
   public long timestamp3;
   public final boolean enabled;
   public ArrayList arrayList;
   public List list = new ArrayList();
   public int count;
   public final CompositeDoubleEntry compositeDoubleEntry;
   public int count2;

   private void applyIndex(int count3) {
      double offset = (double)(System.nanoTime() - this.timestamp2) / 1.0E9;
      if (offset < 2.0) {
         this.list.add(offset);
      }

      if (this.list.size() > 10) {
         this.updateState6();
         this.list.clear();
      }

      this.applyIndex(count3, offset);
   }

   @Override
   public void updateState2() {
   }

   private double computeValue(int count3, int count4) {
      int[] countArray = this.resolveIndexArray(count3);
      int[] countArray2 = this.resolveIndexArray(count4);
      return Math.sqrt((double)((countArray[0] - countArray2[0]) * (countArray[0] - countArray2[0]) + (countArray[1] - countArray2[1]) * (countArray[1] - countArray2[1])));
   }

   public void updateState5() {
      long timestamp4 = System.currentTimeMillis();
      if (this.timestamp3 != 0L) {
         long value = timestamp4 - this.timestamp3;
         this.arrayList.add(value);
         if (this.arrayList.size() > 3) {
            this.arrayList.remove(0);
         }
      }

      this.timestamp3 = timestamp4;
   }

   private double computeValue3(List list2) {
      double offset = 0.0;

      for (Long value : (Iterable<Long>) (Iterable) list2) {
         offset += value.doubleValue();
      }

      return offset == 0.0 ? 0.0 : offset / (double)list2.size();
   }

   private void nanoTime(int count3, int count4) {
      this.count2 = count3;
      this.timestamp2 = System.nanoTime();
      this.count = count4;
   }

   private double computeValue4(List list2) {
      double offset = 0.0;
      double offset2 = 0.0;

      for (Double offset3 : (Iterable<Double>) (Iterable) list2) {
         offset += offset3;
      }

      double offset4 = offset / (double)list2.size();

      for (Double offset5 : (Iterable<Double>) (Iterable) list2) {
         offset2 += (offset5 - offset4) * (offset5 - offset4);
      }

      return Math.sqrt(offset2 / (double)list2.size());
   }

   private void updateState6() {
      byte byteValue = 0;
      int count3 = 329695881;
      short shortValue = 25747;
      List list2 = this.list;
      double offset = this.computeValue4(list2) * 100.0;
      ArrayList arrayList2 = this.arrayList;
      double offset2 = this.computeValue3(arrayList2);
      if (offset < 2.0 && Math.abs(offset2 - 50.0) < 40.0) {
         RayTraceUtil.applyString("Is clicking with regular deviation on items " + DecimalFormatter.formatDecimal(offset, 2) + " deviation");
      }
   }

   private int[] resolveIndexArray(int count3) {
      int count4 = count3 / 9 + 1;
      int count5 = count3 - (count4 - 1) * 9;
      return new int[]{count4, count5};
   }

   @Override
   public void handlePacket(Packet packet) {
      Client.getClient().getClass();
   }

   private void applyIndex(int count3, double offset) {
      Client.getClient().getClass();
   }

   public Intave14InventorySlotCheck(String text, AntiCheatManager antiCheatManager) {
      super(text, antiCheatManager);
      this.arrayList = new ArrayList();
      this.timestamp3 = 0L;
      this.compositeDoubleEntry = new CompositeDoubleEntry(1212, "", DirectOptionType.KILLAURA);
      this.enabled = false;
   }
}
